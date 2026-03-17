package modules.servicecatalog.dto.response;

public class ServiceResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;

    public ServiceResponse(Long id, String name, String description, Double price, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Integer getDuration() { return duration; }
}