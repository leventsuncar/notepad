package com.lsuncar.notepad.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table ( name = "company_user", schema = "public", catalog = "notepad_data")
public class CompanyUser extends BaseEntity{

    @Id
    private Long id;

    @ManyToOne //FIXME ilişki hatalı bir kullanıcı sadece bir companyde olabilir
    @JoinColumn ( name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn ( name = "company_id", referencedColumnName = "id")
    private Company company;

}
