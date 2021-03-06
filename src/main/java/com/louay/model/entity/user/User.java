package com.louay.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyGroup;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 1100592655025621112L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long userId;
    @Column(name = "forename", nullable = false, columnDefinition = "VARCHAR(50)")
    private String forename;
    @Column(name = "surname", nullable = false, columnDefinition = "VARCHAR(50)")
    private String surname;
    @Lob
    @LazyGroup("lobs")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "user_image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] userImage;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_edit_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    private Calendar lastEditDate;
    @Transient
    private MultipartFile userIdMultiPart;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public Calendar getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Calendar lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public StringBuilder getImageBase64Encode() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Base64.getEncoder().encodeToString(this.userImage));

        return stringBuilder;
    }

    public MultipartFile getUserIdMultiPart() {
        return userIdMultiPart;
    }

    public void setUserIdMultiPart(MultipartFile userIdMultiPart) {
        this.userIdMultiPart = userIdMultiPart;
        try {
            this.userImage = userIdMultiPart.getBytes();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transient
    @Override
    public int compareTo(User o) {
        return this.userId.compareTo(o.getUserId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId().equals(user.getUserId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Transient
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", userImage=" + Arrays.toString(userImage) +
                ", lastEditDate=" + lastEditDate +
                '}';
    }
}
