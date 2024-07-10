package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.regex.Pattern;
import io.github.security.SQLSecurity;

public final class SQLTest {

  private Connection conn;

  public ResultSet simpleIndirect() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String sql = "SELECT * FROM " + filterTable(input + "");
    Statement stmt = conn.createStatement();
    return stmt.executeQuery(sql);
  }

  public boolean simplePreparedStatementDirect() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + filterTable(input + ""));
    return stmt.execute();
  }

  public ResultSet deleteStatement() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    Statement stmt = conn.createStatement();
    return stmt.executeQuery("DELETE FROM " + filterTable(input + "") + " WHERE 1=1");
  }

  public ResultSet withQuotes() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    Statement stmt = conn.createStatement();
    return stmt.executeQuery("DELETE FROM \"" + filterTable(input + "") + "\" WHERE 1=1");
  }

  public ResultSet ignoreStatic() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    Statement stmt = conn.createStatement();
    return stmt.executeQuery("SELECT * FROM " + "user_table");
  }
  
  String filterTable(final String tablename) {
      return SQLSecurity.alphanumericValidator(tablename);
  }

}
