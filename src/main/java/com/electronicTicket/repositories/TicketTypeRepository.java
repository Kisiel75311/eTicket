package com.electronicTicket.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.electronicTicket.models.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Optional<TicketType> findByTicketTypeId(Long ticketTypeId);
}
