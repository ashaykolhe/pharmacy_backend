package com.pharmacy.service;

import com.pharmacy.model.ContactDetail;
import com.pharmacy.repository.ContactDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ContactDetailService implements IContactDetailService {
    private final ContactDetailRepository contactDetailRepository;

    @Override
    public ContactDetail saveContactDetail(ContactDetail contactDetail) {
        log.debug("contactDetail saved " + contactDetail);
        return contactDetailRepository.save(contactDetail);
    }

    @Override
    public List<ContactDetail> saveContactDetails(List<ContactDetail> contactDetails) {
        return contactDetailRepository.saveAll(contactDetails);
    }
}
