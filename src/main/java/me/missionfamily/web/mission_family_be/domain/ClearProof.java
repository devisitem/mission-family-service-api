package me.missionfamily.web.mission_family_be.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "mf_clear_proof")
public class ClearProof {

    @Id @GeneratedValue
    @Column(name = "mf_proof_id")
    private Long proofId;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "mf_parent_mission")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_creater")
    private Family creater;

    @Column(name = "mf_proof_type",length = 30)
    private String proofType;

    @Column(name = "mf_proof_path",length = 200)
    private String proofPath;

    @Column(name = "mf_proof_filetype",length = 20)
    private String fileType;

    @Column(name = "mf_proof_content",length = 1000)
    private String proofContent;

    @Column(name = "mf_proof_filesize")
    private int fileSize;

    @Column(name="mf_order_number")
    private int orderNumber;

    @Column(name = "mf_delete_yn",length = 1)
    private String deleteYn;

    @Column(name = "mf_upload_date")
    private LocalDateTime uploadDate;


}
