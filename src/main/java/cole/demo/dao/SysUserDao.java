package cole.demo.dao;

import cole.demo.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  SysUserDao extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
