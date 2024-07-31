package com.app;

import com.app.persitence.entity.PermissionEntity;
import com.app.persitence.entity.RoleEntity;
import com.app.persitence.entity.RoleEnum;
import com.app.persitence.entity.UserEntity;
import com.app.persitence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;
import java.util.List;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {

			//create permissions
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE").build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ").build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE").build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE").build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR").build();

			//create roles
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of( readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//create users
			UserEntity userJesus= UserEntity.builder()
					.username("jesus")
					.password("$2a$10$MJ/A4n7AL5Lw1GvLsFzr8OtMahGe1/TorFzKwaiYC7UWCg2MB9igS")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$MJ/A4n7AL5Lw1GvLsFzr8OtMahGe1/TorFzKwaiYC7UWCg2MB9igS")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password("$2a$10$MJ/A4n7AL5Lw1GvLsFzr8OtMahGe1/TorFzKwaiYC7UWCg2MB9igS")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userJaque = UserEntity.builder()
					.username("jaque")
					.password("$2a$10$MJ/A4n7AL5Lw1GvLsFzr8OtMahGe1/TorFzKwaiYC7UWCg2MB9igS")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userJesus, userJaque, userDaniel, userAndrea));
		};
	}
}
