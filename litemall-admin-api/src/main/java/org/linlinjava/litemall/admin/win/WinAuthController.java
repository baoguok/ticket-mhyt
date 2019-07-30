package org.linlinjava.litemall.admin.win;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallAdmin;
import org.linlinjava.litemall.db.service.LitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/win/auth")
@Validated
public class WinAuthController {

		@Autowired
		private LitemallAdminService adminService;

		/**
		 * 人工售票窗口---登陆
		 * @param body { username : value, password : value }
		 * @return
		 */
		@PostMapping("/login")
		public Object login(@RequestBody String body) {
				String username = JacksonUtil.parseString(body, "username");
				String password = JacksonUtil.parseString(body, "password");

				if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
						return ResponseUtil.badArgument();
				}
				LitemallAdmin admin = adminService.selectUser(username);
				Map<String, Object> data = new HashMap<>();
				data.put("data", admin);

				Subject currentUser = SecurityUtils.getSubject();
				try {
						currentUser.login(new UsernamePasswordToken(username, password));
				} catch (UnknownAccountException uae) {
						return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
				} catch (LockedAccountException lae) {
						return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");
				} catch (AuthenticationException ae) {
						return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, ae.getMessage());
				}
				// return ResponseUtil.ok(currentUser.getSession().getId());
				return ResponseUtil.ok(data);
		}


		@GetMapping("/401")
		public Object page401() {
				return ResponseUtil.unlogin();
		}

		@GetMapping("/index")
		public Object pageIndex() {
				return ResponseUtil.ok();
		}

		@GetMapping("/403")
		public Object page403() {
				return ResponseUtil.unauthz();
		}
}
