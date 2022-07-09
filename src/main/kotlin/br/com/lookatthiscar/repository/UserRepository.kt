package br.com.lookatthiscar.repository

import br.com.lookatthiscar.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}