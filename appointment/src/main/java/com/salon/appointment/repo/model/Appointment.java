package com.salon.appointment.repo.model;
import javax.persistence.*;

@Entity
@Table(name = "appointment", schema = "appointment")
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_appointment")
    private long idAppointment;

    @Column(name = "user_id")
    private long idUser;

    @Column(name = "service_id")
    private long idService;

    @Column(name = "status_id")
    private long status;

    public long getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(long idAppointment) {
        this.idAppointment = idAppointment;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
