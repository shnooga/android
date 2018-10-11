package com.slewsoft.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Job {
    private int id;
    private String address;
    private Customer customer;
    private String notes;
    private List<Site> sites = new ArrayList<>();
    private Status status = Status.Active;


    public enum Status {
        Active(1, "Active"),
        Paid(2, "Paid"),
        Closed(3, "Closed"),
        Billed(4, "Billed");

        private int id;
        private String desc;

        Status(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<Site> getSites() { return sites; }
    public void setSites(List<Site> sites) { this.sites = sites; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setDateFormat(sdf);

        Job job = createJob("Sesame st", "some note", Status.Billed);

        job.setCustomer(createCustomer());
        job.getSites().add(createSite("-34.8799074,174.7565664", 50, 10));
        job.getSites().add(createSite("-24.8799074,144.7565664",80, 15));

        try {
            String jsonStr = mapper.writeValueAsString(job);
            System.out.println(jsonStr);


            Job deSerJob = mapper.readValue(jsonStr, Job.class);
            System.out.println(deSerJob);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Job createJob(String address, String notes, Status status) {
        Job job = new Job();

        job.setAddress(address);
        job.setNotes(notes);
        job.setStatus(status);
        return job;
    }

    private static Customer createCustomer() {
        Customer customer = new Customer("Costco", "123 Sesame St", "Joe Super", "707.423.1169");

        customer.setId(777);
        customer.setBillAddress("234 Ez Str");
        return customer;
    }

    private static Site createSite(String latLng, int bldHeight, int unitOffset) {
        Site site = new Site(latLng, bldHeight, unitOffset);

        Asset a;
        a = new Asset();
        a.setType(Asset.AssetType.Crane);
        a.setWeight(40);
        site.getAssets().add(a);
        a = new Asset();
        a.setType(Asset.AssetType.Hvac);
        a.setWeight(700);
        a.setUnitWeightType(Asset.UnitWeightType.Pound);
        site.getAssets().add(a);
        a = new Asset();
        a.setType(Asset.AssetType.Truck);
        a.setWeight(10);
        site.getAssets().add(a);
        return site;
    }
}
