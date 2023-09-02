package permissions.application.create_permission

import shared.application.BaseUseCase
import zio._
import permissions.domain.repository.PermissionRepository
import permissions.domain.entity.Permission

class CreatePermissionUseCase (
  using permissionRepository: PermissionRepository
) extends BaseUseCase[RequestCreatePermission, ResponseCreatePermission]:

  private val EMPTY_ID = -1

  override def execute(request: RequestCreatePermission): Task[ResponseCreatePermission] =
    ZIO.succeed {
      val created = permissionRepository.insertPermission(
        Permission(
          id = EMPTY_ID,
          name = request.name,
          accessModule = request.accessModule,
          roleId = request.roleId,
        )
      )
      ResponseCreatePermission(created)
    }
