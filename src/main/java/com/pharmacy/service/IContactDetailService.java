package com.pharmacy.service;

import com.pharmacy.model.ContactDetail;

import java.util.List;

public interface IContactDetailService {
    ContactDetail saveContactDetail(ContactDetail sontactDetail);

    List<ContactDetail> saveContactDetails(List<ContactDetail> sontactDetails);
}
