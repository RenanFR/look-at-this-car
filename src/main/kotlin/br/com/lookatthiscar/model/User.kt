package br.com.lookatthiscar.model

import javax.persistence.*

@Entity
@Table(name = "user_tbl")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    var password: String
)
