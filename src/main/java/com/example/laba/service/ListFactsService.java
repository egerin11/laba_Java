package com.example.laba.service;

import com.example.laba.model.CatFact;
import com.example.laba.model.MyListFact;
import com.example.laba.repository.dao.MyListFactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ListFactsService {

    @Autowired
    private final MyListFactRepository myListFactRepository;
    private final FactService factService;

    public MyListFact addToList(MyListFact myListFact) {
        Long myListFactId = myListFact.getId();
        MyListFact existingMyListFact = myListFactRepository.findById(myListFactId).orElseThrow(() -> new IllegalArgumentException("запись MyListFact с указанным идентификатором не найдена"));
        existingMyListFact.getCatFacts().addAll(myListFact.getCatFacts());
        return myListFactRepository.save(existingMyListFact);

    }

    public List<MyListFact> getFactsFromList() {
        return StreamSupport.
                stream(myListFactRepository.findAll().spliterator(), false).toList();
    }

    public MyListFact getFact(Long id) {
        return myListFactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + id));
    }

    public MyListFact removeList(Long id) {
        MyListFact myListFact = getFact(id);
        myListFactRepository.delete(myListFact);
        return myListFact;
    }

    public MyListFact addFactToList(Long id, Long idList) {
        MyListFact oldItem = getFact(idList);
        CatFact catFact = factService.getFact(id);
        oldItem.addFact(catFact);
        myListFactRepository.save(oldItem);
        return oldItem;
    }

    public MyListFact removeFactToList(Long id, Long idList) {
        MyListFact oldItem = getFact(idList);
        CatFact catFact = factService.getFact(id);
        oldItem.removeFact(catFact);
        myListFactRepository.save(oldItem);
        return oldItem;
    }
}
