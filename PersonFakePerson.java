package com.notePad.notepad.Dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class   PersonFakePerson implements Person {
    private static List<com.notePad.notepad.Model.Person> DB = new ArrayList<>();


    @Override
    public int insertPerson(UUID id, com.notePad.notepad.Model.Person person) {
        DB.add(new com.notePad.notepad.Model.Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<com.notePad.notepad.Model.Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<com.notePad.notepad.Model.Person> selectPersonById(UUID id) {

        return DB.stream().filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<com.notePad.notepad.Model.Person> personMaybe=selectPersonById(id);
       if(personMaybe.isEmpty()){
           return 0;
       }
       DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonByd(UUID id, com.notePad.notepad.Model.Person person) {
        return selectPersonById(id).
                map(person1 -> {int index= DB.indexOf(person1);
                if(index>=0){
                  DB.set(index,new com.notePad.notepad.Model.Person(id,person.getName()));
                  return 1;
                }
                    return 0;
                }) .orElse(0);
    }

}
