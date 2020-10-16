/**
 * @author Vinod Parlapalli
 * Created on 2019/12/06
 */

package in.rgukt.r081247.bankingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.rgukt.r081247.bankingapi.model.type.TransactionStatus;
import in.rgukt.r081247.bankingapi.model.type.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction implements Comparable<Transaction> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "type should not be null.")
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @NotNull(message = "amount should not be null.")
    @Min(value = 1)
    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "to_user")
    private User toUser;

    //@NotNull(message = "status should not be null.")
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name = "message", length = 1024, nullable = true)
    private String message;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    public Transaction() {
    }

    public Transaction(TransactionType type,
                       Long amount,
                       User fromUser,
                       User toUser,
                       TransactionStatus status,
                       String message,
                       LocalDateTime createdTime) {
        this.type = type;
        this.amount = amount;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.status = status;
        this.message = message;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Transaction transaction) {
        return -(this.id.compareTo(transaction.id));
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", fromUser=" + (fromUser != null ? fromUser.getUsername() : "") +
                ", toUser=" + (toUser != null ? toUser.getUsername() : "") +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
