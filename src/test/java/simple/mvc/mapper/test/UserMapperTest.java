package simple.mvc.mapper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import simple.mvc.app.mapper.UserMapper;
import simple.mvc.app.mapper.impl.UserMapperImpl;
import simple.mvc.bean.UserBean;
import simple.mvc.jpa.Role;

public class UserMapperTest {

  private UserMapper userMapper = new UserMapperImpl();
  
  @Test
  public void rolesDifferenceShouldReturnNewRole() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","GUEST");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    assertEquals(newRoleSet, userMapper.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOldRole() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","GUEST");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("USER");
    assertEquals(oldRoleSet, userMapper.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    newRoleSet.add("USER");
    assertEquals(newRoleSet, userMapper.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOnlyNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("GUEST","USER");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    newRoleSet.add("USER");
    assertEquals(newRoleSet, userMapper.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNoNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoleSet = new HashSet<String>();
    assertEquals(newRoleSet, userMapper.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNoOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> oldRoleSet = new HashSet<String>();
    assertEquals(oldRoleSet, userMapper.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("MANAGER","GUEST","ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("GUEST");
    oldRoleSet.add("USER");
    assertEquals(oldRoleSet, userMapper.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOnlyOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("GUEST","USER");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("ADMIN");
    oldRoleSet.add("MANAGER");
    assertEquals(oldRoleSet, userMapper.getOldRolesDifference(oldRoles, newRoles));
  }
}
