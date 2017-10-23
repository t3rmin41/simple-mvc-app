package simple.mvc.service.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.Test;

import simple.mvc.service.UserService;
import simple.mvc.service.impl.UserServiceImpl;

public class UserServiceTest {

  private UserService userService = new UserServiceImpl();
  
  @Test
  public void rolesDifferenceShouldReturnNewRole() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","GUEST");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    assertEquals(newRoleSet, userService.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOldRole() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","GUEST");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("USER");
    assertEquals(oldRoleSet, userService.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    newRoleSet.add("USER");
    assertEquals(newRoleSet, userService.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOnlyNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("GUEST","USER");
    Set<String> newRoleSet = new HashSet<String>();
    newRoleSet.add("GUEST");
    newRoleSet.add("USER");
    assertEquals(newRoleSet, userService.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNoNewRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoleSet = new HashSet<String>();
    assertEquals(newRoleSet, userService.getNewRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnNoOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("MANAGER","ADMIN","GUEST","USER");
    Set<String> oldRoleSet = new HashSet<String>();
    assertEquals(oldRoleSet, userService.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("MANAGER","GUEST","ADMIN","USER");
    Set<String> newRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("GUEST");
    oldRoleSet.add("USER");
    assertEquals(oldRoleSet, userService.getOldRolesDifference(oldRoles, newRoles));
  }
  
  @Test
  public void rolesDifferenceShouldReturnOnlyOldRoles() {
    Set<String> oldRoles = Sets.newLinkedHashSet("ADMIN","MANAGER");
    Set<String> newRoles = Sets.newLinkedHashSet("GUEST","USER");
    Set<String> oldRoleSet = new HashSet<String>();
    oldRoleSet.add("ADMIN");
    oldRoleSet.add("MANAGER");
    assertEquals(oldRoleSet, userService.getOldRolesDifference(oldRoles, newRoles));
  }
}
