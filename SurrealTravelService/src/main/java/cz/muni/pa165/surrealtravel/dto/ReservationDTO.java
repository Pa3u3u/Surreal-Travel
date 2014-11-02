/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.surrealtravel.dto;

import cz.muni.pa165.surrealtravel.entity.Customer;
import cz.muni.pa165.surrealtravel.entity.Excursion;
import cz.muni.pa165.surrealtravel.entity.Trip;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomáš Kácel [359965]
 */
public class ReservationDTO {
    private long id=0;
    private Customer customer;
    private Trip trip;
    private List<Excursion> excursions;
    
    public ReservationDTO() {
        excursions = new ArrayList<>();
        
        
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer customer){
        this.customer= customer;
    }

    public Trip getTrip() {
        return trip;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }
    
    public void addExcursion(Excursion excursion){
        if(excursion==null)throw new NullPointerException("nejede");
        this.excursions.add(excursion);
    }
    
    public void removeExcursion(Excursion excursion){
        this.excursions.remove(excursion);
    }
    
    public BigDecimal getTotalPrice(){
        BigDecimal base= this.getTrip().getBasePrice();
        //List<Excursion> excursio=this.getExcursions();
        //if(excursions.isEmpty()){return base;}
        BigDecimal dc=new BigDecimal(0);
        for(Excursion ext:getExcursions()){
          dc= dc.add(ext.getPrice());
        }
        dc=dc.add(base);
        return dc;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationDTO other = (ReservationDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.trip != other.trip && (this.trip == null || !this.trip.equals(other.trip))) {
            return false;
        }
        if (this.excursions != other.excursions && (this.excursions == null || !this.excursions.equals(other.excursions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", customer=" + customer + ", trip=" + trip + ", excursions=" + excursions + '}';
    }
}
