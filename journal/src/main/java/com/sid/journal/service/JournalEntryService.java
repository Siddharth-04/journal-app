package com.sid.journal.service;

import java.util.*;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sid.journal.entity.JournalEntry;
import com.sid.journal.entity.User;
import com.sid.journal.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry savedOne = journalEntryRepository.save(journalEntry);
            user.getArr().add(savedOne);
            userService.saveUser(user);
        }catch (Exception e){
            logger.info("hiiii logger");
            throw new RuntimeException("Error saving journal entry",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteJournalEntryById(ObjectId id, String userName){
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed =  user.getArr().removeIf(x -> x.getId().equals(id)); //agar journalKiId equal hogi parameter mein aayi id se toh voh id user ki list mein se delete ho jayegi
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error while deleting journal entry",e);
        }

        return removed;


    }


}
