package com.pharmacy.repository;

import com.pharmacy.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {
}
