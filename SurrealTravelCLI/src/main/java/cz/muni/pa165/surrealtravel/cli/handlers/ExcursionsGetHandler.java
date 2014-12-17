package cz.muni.pa165.surrealtravel.cli.handlers;

import cz.muni.pa165.surrealtravel.Command;
import cz.muni.pa165.surrealtravel.MainOptions;
import cz.muni.pa165.surrealtravel.cli.rest.RestExcursionClient;
import cz.muni.pa165.surrealtravel.dto.ExcursionDTO;
import org.kohsuke.args4j.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Klimeš
 */
@Component
public class ExcursionsGetHandler implements CommandHandler {

    @Autowired(required = true)
    private RestExcursionClient client;
    
    @Option(name = "--id", metaVar = "id", usage = "specify the excursion id", required = true)
    private long id;
    
    @Override
    public Command getCommand() {
        return Command.EXCURSIONS_GET;
    }

    @Override
    public String getDescription() {
        return "list excursions with the given id";
    }

    @Override
    public void run(MainOptions options) {
        ExcursionDTO excursion = client.getExcursion(id);
        
        if(excursion == null) {
            System.out.println("NO EXCURSION FOUND");
            return;
        }
        
        System.out.println("banzaaaaj!");
        System.out.println(excursion.getDestination()); // just testing
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}