package com.electronicTicket.repositories;

import com.electronicTicket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByTicketCode(Long ticketCode);

    List<Ticket> findByExpirationDateBetween(Date startDate, Date endDate);

}
