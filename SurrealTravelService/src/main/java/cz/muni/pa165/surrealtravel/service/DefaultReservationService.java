package cz.muni.pa165.surrealtravel.service;

import cz.muni.pa165.surrealtravel.dao.ReservationDAO;
import cz.muni.pa165.surrealtravel.dto.CustomerDTO;
import cz.muni.pa165.surrealtravel.dto.ExcursionDTO;
import cz.muni.pa165.surrealtravel.dto.ReservationDTO;
import cz.muni.pa165.surrealtravel.entity.Customer;
import cz.muni.pa165.surrealtravel.entity.Excursion;
import cz.muni.pa165.surrealtravel.entity.Reservation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomáš Kácel [359965]
 */
@Service(value="reservationService")
public class DefaultReservationService implements ReservationService {
  @Autowired
  private ReservationDAO reservationDAO;
  @Autowired
  private DozerBeanMapper mapper;
  
  /**
   * save trip
   * @param reservationDTO  the DTO of reservation 
   */
  @Transactional
    @Override
  public void addReservation(ReservationDTO reservationDTO){
      validateReservation(reservationDTO );
      Reservation reservation=mapper.map(reservationDTO, Reservation.class);
      reservationDAO.addReservation(reservation);
      reservationDTO.setId(reservation.getId());
  }
  /**
   * get all reservation with selected id
   * @param id the id of reservation
   * @return result reservation with selected id
   */
    @Override
  public ReservationDTO getReservationById(long id){
      Reservation reservation = reservationDAO.getReservationById(id);
      ReservationDTO result = mapper.map(reservation,ReservationDTO.class);
      return result;
  }
  /**
   * return list of al reservation 
   * @return list of reservation
   */
    @Override
  public List<ReservationDTO> getAllReservations(){
      List<ReservationDTO> result = new ArrayList<>();
      List<Reservation> reservations = reservationDAO.getAllReservations();
      for (Reservation reservation : reservations) {
            result.add(mapper.map(reservation,ReservationDTO.class));
        }
      return result;
  }
  /**
   * return list of all reservation for selected customer
   * @param customerDTO the customer for we need the reservations
   * @return 
   */
    @Override
  public List<ReservationDTO> getAllReservationsByCustomer(CustomerDTO customerDTO){
      Objects.requireNonNull(customerDTO);
      List<ReservationDTO> result = new ArrayList<>();
      //Reservation reservation=mapper.map(reservationDTO, Reservation.class);
      Customer customer=mapper.map(customerDTO, Customer.class);
      List<Reservation> reservations = reservationDAO.getAllReservationsByCustomer(customer);
      for (Reservation reservation : reservations) {
            result.add(mapper.map(reservation,ReservationDTO.class));
        }
      return result;
  }
  /**
   * get list with all reservation with selected excursion
   * @param excursionDTO the selected excursion we want all reservation from there
   * @return 
   */
    @Override
  public List<ReservationDTO> getAllReservationsByExcursion(ExcursionDTO excursionDTO){
      Objects.requireNonNull(excursionDTO);
      List<ReservationDTO> result = new ArrayList<>();
      Excursion excursion=mapper.map(excursionDTO, Excursion.class);
      List<Reservation> reservations = reservationDAO.getAllReservationsByExcursion(excursion);
      for (Reservation reservation : reservations) {
            result.add(mapper.map(reservation,ReservationDTO.class));
        }
      return result;
  }
  
  /**
   * update reservation data
   * @param reservationDTO the reservation with data to update
   */
  @Transactional
    @Override
  public void updateReservation(ReservationDTO reservationDTO){
     validateReservation(reservationDTO);
     Reservation reservation=mapper.map(reservationDTO, Reservation.class);
     reservationDAO.updateReservation(reservation);
      
  }
  /**
   * delete reservation from databaze
   * @param reservationDTO the reservation to delete
   */
  @Transactional
    @Override
  public void deleteReservation(ReservationDTO reservationDTO){
      Objects.requireNonNull(reservationDTO);
      Reservation reservation=mapper.map(reservationDTO, Reservation.class);
      reservationDAO.deleteReservation(reservation);
  }
  /**
   * get full price to pay for one customer
   * @param customerDTO the customer, for him we calculate the price
   * @return 
   */
  @Transactional
    @Override
  public BigDecimal getFullPriceByCustomer(CustomerDTO customerDTO){
      Objects.requireNonNull(customerDTO);
      Customer customer=mapper.map(customerDTO, Customer.class);
      BigDecimal result= reservationDAO.getFullPriceByCustomer(customer);
      return result;
  }
  /**
   * remove excursion from all reservations
   * @param excursionDTO the reservation which must be remove
   */
  @Transactional
    @Override
  public void removeExcursionFromAllReservations(ExcursionDTO excursionDTO){
      Objects.requireNonNull(excursionDTO);
      Excursion excursion=mapper.map(excursionDTO, Excursion.class);
      reservationDAO.removeExcursionFromAllReservations(excursion);
  }
/**
 * simple validation
 * @param reservationDTO to validate
 */
    private void validateReservation(ReservationDTO reservationDTO) {
        if (reservationDTO.getCustomer() == null) {
            throw new IllegalArgumentException("Customer must exist");
        }
        
    }
/**
 * setter for reservation dato
 * @param reservationDAO to set
 */
    @Override
    public void setReservationDAO(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }
/**
 * seter to maper
 * @param mapper to set
 */
    @Override
    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
    
}