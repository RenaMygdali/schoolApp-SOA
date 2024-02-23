package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.controller_view.*;

import java.awt.EventQueue;

public class Main {
	private static Menu menu;
	private static InsertForm insertForm;
	private static UpdateDeleteForm updateDeleteForm;
	private static SearchForm searchForm;
	private static UsersSearchForm usersSearchForm;
	private static UpdateDeleteUsersForm updateDeleteUsersForm;

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

					updateDeleteForm = new UpdateDeleteForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
//
                    usersSearchForm = new UsersSearchForm();
                    usersSearchForm.setLocationRelativeTo(null);
                    usersSearchForm.setVisible(false);

					updateDeleteUsersForm = new UpdateDeleteUsersForm();
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

	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}

	public static UsersSearchForm getUsersSearchForm() {
		return usersSearchForm;
	}

	public static UpdateDeleteUsersForm getUpdateDeleteUsersForm() {
		return updateDeleteUsersForm;
	}
}
