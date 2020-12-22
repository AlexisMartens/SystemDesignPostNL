package be.ugent.systemdesign.group16;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.group16.application.KoerierService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModel;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModelJpaRepository;
import be.ugent.systemdesign.group16.infrastructure.OrderDataModel;
import be.ugent.systemdesign.group16.infrastructure.OrderDataModelJpaRepository;

@SpringBootApplication
public class KoerierServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(KoerierServiceApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(KoerierServiceApplication.class, args);
	}
	
	private static void logKoerierDataModels(List<KoerierDataModel> koeriers) {
		log.info("-Number of koeriersDM found: {}", koeriers.size());
		for(KoerierDataModel koerier : koeriers) {
			log.info("--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}           "
					,koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(), koerier.getVervoercapaciteit()
					);
		}
	}
	
	@Bean
	CommandLineRunner testKoerierDataModelJpaRepository(KoerierDataModelJpaRepository repo) {
		return (args) ->{
			log.info("$Testing KoerierDataModelJPARepository.");
			  
			log.info(">Find all Koeriers from database."); 
			List<KoerierDataModel> koeriersAll = repo.findAll(); 
			logKoerierDataModels(koeriersAll);
			
			log.info(">Find all koeriers by postcodeRonde {} from database.",9000);
			List<KoerierDataModel> koerierByPostcodeRonde = repo.findByPostcodeRonde("9000");
			logKoerierDataModels(koerierByPostcodeRonde);
		
			log.info(">Save new Koerier to database.");
			KoerierDataModel newKoerier = new KoerierDataModel(10,"Bert","9900",50);
			repo.saveAndFlush(newKoerier);
			Integer newKoerierId = newKoerier.getKoerierId();
			
			log.info(">Find Koerier by id {} from database.", newKoerierId);
			Optional<KoerierDataModel> koerierById= repo.findById(newKoerierId);
			koerierById.ifPresentOrElse( (value) -> {
				logKoerierDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logKoerierDataModels(Collections.emptyList()); } );
			  
			log.info(">Delete Bestelling by id {} from database.", newKoerierId);
			repo.deleteById(newKoerierId);
		};
	}
	
	
	private static void logKoeriers(List<Koerier> koeriers) {
		log.info("-Number of koeriers found: {}", koeriers.size());
		for(Koerier koerier : koeriers) {
			log.info("--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}          "
					,koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(), koerier.getVervoercapaciteit());
		}
	}
	
	@Bean
	CommandLineRunner testKoerierRepository(KoerierRepository repo) {
		return (args) -> {
			log.info("$Testing KoerierRepository.");
			
			log.info(">Find one koerier by id {} from database.", 1);
			Koerier koerierById = repo.findOne(1);
			logKoeriers(Collections.unmodifiableList(Arrays.asList(koerierById)));
			
			Integer newKoerierId = 5;
			log.info(">Save new koerier with id {} to database.", newKoerierId);
			Koerier newKoerier = new Koerier(newKoerierId,"Bert","9900",50);
			repo.save(newKoerier);
			
			log.info(">Find all koeriers that have postcodeRonde {}", "9000");
			List<Koerier> koeriers = repo.findByPostcodeRonde("9000");
			logKoeriers(koeriers);
		};
	}
	
	private static void logOrderDataModels(List<OrderDataModel> orders) {
		log.info("-Number of orders found: {}", orders.size());
		for(OrderDataModel order : orders) {
			log.info("--orderId {}       , naamAfzender {}   ..............."
					, order.getOrderId()   , order.getNaamAfzender()
					);
		}
	}
	
	@Bean
	CommandLineRunner testOrderDataModelJpaRepository(OrderDataModelJpaRepository Orepo, KoerierDataModelJpaRepository Krepo) {
		return (args) ->{
			log.info("$Testing OrderDataModelJpaRepository.");
			  
			log.info(">Find all Orders from database."); 
			List<OrderDataModel> ordersAll = Orepo.findAll(); 
			logOrderDataModels(ordersAll);
			
			
			KoerierDataModel koerierDM= Krepo.findById(1).orElse(null);;
			Integer numberOfOrders = Orepo.countByKoerierDataModel(koerierDM);
			log.info("-Number of orders found for Koerier 1: {}", numberOfOrders);

		
			log.info(">Save new Order to database.");
			OrderDataModel newOrder = new OrderDataModel(1,koerierDM,"Jan klaasen" ,"9000"      ,"griekstraat 5" ,"Gent"   ,"Belgie"   , "Geert Klaasen" ,"9100" 
					,"klopperstraat 5","Sint-Niklaas"   , "Belgie"  , LocalDate.now(), false, false, "OP_TE_HALEN");
			Orepo.saveAndFlush(newOrder);
			Integer newOrderId = newOrder.getOrderId();
			
			log.info(">Find Order by id {} from database.", newOrderId);
			Optional<OrderDataModel> orderById= Orepo.findById(newOrderId);
			orderById.ifPresentOrElse( (value) -> {
				logOrderDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logOrderDataModels(Collections.emptyList()); } );
			log.info(">Delete Order by id {} from database.", newOrderId);
			Orepo.deleteById(newOrderId);
		};
	}
	
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}
	
	@Bean
	CommandLineRunner testInpatientService(KoerierService service, KoerierRepository repo) {
		return (args) -> {
			log.info("$Testing KoerierService.");
			Response response;
			
			log.info(">stuurKoerier (success).");
			Koerier koerierById = repo.findOne(1);
			response = service.stuurKoerier(50,koerierById,new Adres("Jan Vander Broek", "9000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "9900", "geefstraat 4", "Geverghem", "Belgie"), LocalDate.of(2020,5,4), true, false, false );
			logResponse(response);
			
			log.info(">stuurKoerier (fail).");
			Koerier koerierById2 = repo.findOne(1);
			response = service.stuurKoerier(50,koerierById2,new Adres("Jan Vander Broek", "0000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "0000", "geefstraat 4", "Geverghem", "Belgie"), LocalDate.of(2020,5,4), true, false, false );
			logResponse(response);
			
			/*log.info(">bevestigAfleverenBuren (success).");
			response = service.bevestigAfleverenBuren(1);
			logResponse(response);
			
			log.info(">bevestigAfleveren (success).");
			response = service.bevestigAfleveren(2);
			logResponse(response);
			
			log.info(">bevestigOphalen (success).");
			response = service.bevestigOphalen(3);
			logResponse(response);*/
		};
	}
	

}
