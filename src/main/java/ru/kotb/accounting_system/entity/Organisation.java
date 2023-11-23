package ru.kotb.accounting_system.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


/**
 * The class that describes the contracting company.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organisations")
public class Organisation extends AbstractEntity {

    /**
     * The counterparty company name.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=150, message = "The maximum field length is limited to 150 characters")
    @Column(name = "name")
    private String name;

    /**
     * The company address.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(max=150, message = "The maximum field length is limited to 150 characters")
    @Column(name = "address")
    private String address;

    /**
     * The unique code that regulates the accounting of taxpayers.
     */
    @NotBlank(message = "The field cannot be empty")
    @Length(min=10, max=10, message = "TIN must contain 10 digits")
    @Pattern(regexp = "^[0-9]*$", message = "Please provide the valid TIN")
    @Column(name = "TIN")
    private String TIN;

    /**
     * Counterparty contracts that linked with this organisation.
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="organisation_id")
    private List<CounterpartyContract> contracts = new ArrayList<>();
}
