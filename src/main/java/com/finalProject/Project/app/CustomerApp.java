package com.finalProject.Project.app;

import com.finalProject.Project.entity.*;
import com.finalProject.Project.service.imp.CustomerServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Setter
@Component
public class CustomerApp {
    @Autowired
   private CustomerServiceImpl service;
    private Customer customer;
    private Scanner scanner = new Scanner(System.in);
    private Utility utility = new Utility();


    public void createOrder() {
        try {
            System.out.println("insert des");
            String describe = scanner.next();
            System.out.println("adress");
            String address = scanner.next();

            List<SubService> services = service.allSubService();
            for (SubService service : services) {
                System.out.print(service.getName() + "  ");
                System.out.print(service.getBasePrice());
            }
            System.out.println("insert name service for order");
            String nameService = scanner.next();
            SubService subService = services.stream()
                    .filter(p -> p.getName().equals(nameService))
                    .findFirst().get();
            System.out.println("please insert offer price");
            Integer price = scanner.nextInt();
            checkPrice(subService, price);
            LocalDateTime time = utility.dateTime();
            com.finalProject.Project.entity.Order order = new com.finalProject.Project.entity.Order(null, null, price, time, address, describe, customer, subService);
//            service.insertOrder(order);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPrice(SubService service, Integer price) {
        if (service.getBasePrice() > price)
            throw new RuntimeException("bad price");
    }

    public void changePassword() {
        String newPassword = utility.setPassword();
        service.changePassword(this.customer,newPassword);

    }

    public void seeMyOrder() {
        try {
            List<Order> order = service.findMyOrder(customer.getId());
            for (com.finalProject.Project.entity.Order orders1 : order) {
                System.out.println(orders1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choiceOffer() {
        try {
            List<Order> order = service.findMyOrder(customer.getId());
            for (com.finalProject.Project.entity.Order orders1 : order)
                System.out.println(orders1);
            Integer id = scanner.nextInt();
            List<Offer> offers = service.findOfferByOrderId(id);
            offers.stream().forEach(System.out::println);
            sortOfferByPrice(offers);
            Integer idOffer = scanner.nextInt();
            Offer offer = offers.stream()
                    .filter(p -> p.getId().equals(idOffer))
                    .findFirst().get();
            Order order1 = order.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst().get();
            offers.remove(offer);
//            service.choiceOffer(order1, offer, offers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payForOrder() {
        try {
            List<Order> acceptOffer = service.myDownOrder(customer.getId());
            acceptOffer.stream().forEach(System.out::println);
            Integer orderId = scanner.nextInt();
            Order order = acceptOffer.stream()
                    .filter(p -> p.getId().equals(orderId))
                    .findFirst().get();
            System.out.println("insert rate");
            Integer rate = scanner.nextInt();
            service.paying(order, rate);
            addComment(order);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void addComment(Order order) {
        try {
            System.out.println("insert comment");
            String comment = scanner.nextLine();
            Comment comment1 = new Comment(null, null, comment, this.customer);
            Offer offer = order.getOffer();
            service.addComment(offer, comment1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortOfferByPrice(List<Offer> offers) {
        var offerss = offers.stream().sorted(new Comparator<Offer>() {
            @Override
            public int compare(Offer offer, Offer t1) {
                return offer.getOfferPrice().compareTo(t1.getOfferPrice());
            }
        }).collect(Collectors.toList());
        for (Offer offer : offerss) {
            System.out.println(offer.getId());
            System.out.println(offer.getOfferPrice());

        }
    }


}


