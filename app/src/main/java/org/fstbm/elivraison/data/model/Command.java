package org.fstbm.elivraison.data.model;

import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {

        private long id;
        private String from;
        private String destination;
        private String state;

        private Client client;
        private List<Produit> products;

        private Position position;





        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getDestination() {
            return destination;
        }
        public void setDestination(String destination) {
            this.destination = destination;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
        public List<Produit> getProducts() {
            return products;
        }
        public void setProducts(List<Produit> products) {
            this.products = products;
        }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}


