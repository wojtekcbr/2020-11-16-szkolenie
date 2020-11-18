@BDD
  Feature: User Login

    @Login @Wordpress @UserPanel
    Scenario: Login
      Given User start on main page
      When User logs to user panel
      Then User can modify user profile