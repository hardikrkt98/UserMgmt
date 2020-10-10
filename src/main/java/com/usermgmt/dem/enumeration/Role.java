package com.usermgmt.dem.enumeration;

import static com.usermgmt.dem.constants.Authority.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES,
    ROLE_USER(HR_AUTHORITIES,
    ROLE_USER(MANAGER_AUTHORITIES,
     ROLE_USER(ADMIN_AUTHORITIES,
     ROLE_USER(SUPER_USER_AUTHORITIES;

   private String[] authorities;

   Role(String ... authorities)
   {this.authorities = authorities;

   }
  public String[] getAuthorities()
  {
      return authorities;

  }


}
