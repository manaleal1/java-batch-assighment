package homework.week4.day17.domain.dto;

public class PersonResponseDTO {
    private String name;

    public PersonResponseDTO(String name){ this.name = name; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }
}
