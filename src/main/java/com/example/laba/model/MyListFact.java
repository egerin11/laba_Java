    package com.example.laba.model;

    import com.example.laba.model.dto.MyListFactDto;
    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Data
    @Table(name = "list_facts")
    public class MyListFact {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
        @JoinColumn(name = "fact_id",referencedColumnName = "id")
        private List<CatFact> catFacts = new ArrayList<>();

        public void addFact(CatFact catFact) {
            catFacts.add(catFact);
        }

        public void removeFact(CatFact catFact) {
            catFacts.remove(catFact);
     }

        public static MyListFact from(MyListFactDto myListFactDto) {
            MyListFact myListFact = new MyListFact();
            myListFact.setCatFacts(myListFactDto.getCatFacts());
            return myListFact;
        }
    }