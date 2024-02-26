package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.controller_view.*;

import java.awt.EventQueue;

public class Main {
	private static Menu menu;
	private static InsertForm insertForm;
	private static InsertUserForm insertUserForm;
	private static UpdateDeleteForm updateDeleteForm;
	private static SearchForm searchForm;
	private static SearchUserForm searchUserForm;
	private static UpdateDeleteUserForm updateDeleteUsersForm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(true);

					searchForm = new SearchForm();
					searchForm.setLocationRelativeTo(null);
					searchForm.setVisible(false);

					insertForm = new InsertForm();
					insertForm.setLocationRelativeTo(null);
					insertForm.setVisible(false);

					insertUserForm = new InsertUserForm();
					insertUserForm.setLocationRelativeTo(null);
					insertUserForm.setVisible(false);

					updateDeleteForm = new UpdateDeleteForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
//
                    searchUserForm = new SearchUserForm();
                    searchUserForm.setLocationRelativeTo(null);
                    searchUserForm.setVisible(false);

					updateDeleteUsersForm = new UpdateDeleteUserForm();
					updateDeleteUsersForm.setLocationRelativeTo(null);
					updateDeleteUsersForm.setVisible(false);

//					version = new Version();
//					version.setLocationRelativeTo(null);
//					version.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}

	public static SearchForm getSearchForm() {
		return searchForm;
	}

	public static InsertForm getInsertForm() {
		return insertForm;
	}

	public static InsertUserForm getInsertUserForm() {
		return insertUserForm;
	}

	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}

	public static SearchUserForm getSearchUserForm() {
		return searchUserForm;
	}

	public static UpdateDeleteUserForm getUpdateDeleteUsersForm() {
		return updateDeleteUsersForm;
	}
}
