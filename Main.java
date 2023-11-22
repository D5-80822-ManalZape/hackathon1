package com.sunbeam;

import java.util.*;

public class Main {
	public static Scanner sc;
	
	public static int mainMenu(Scanner sc) {
		int choice=0;
		System.out.println("====================================");
		System.out.println("0. Exit");
		System.out.println("1. Edit Profile");
		System.out.println("2. Change Password");
		System.out.println("3. Display All movies");
		System.out.println("4. Create a Review");
		System.out.println("5. Edit Review");		
		System.out.println("6. Delete Review");
		System.out.println("7. Display All Reviews");
		System.out.println("8. Display My Reviews");
		System.out.println("9. Display Reviews shared with me");
		System.out.println("10. Share Review");
		System.out.println("====================================");
		System.out.print("Enter you choice : ");
		try {
		choice = sc.nextInt();
		} catch(InputMismatchException e) {
			System.err.println("Input mismatched");
		}
		return choice;
	}
	
	public static void mainMenu() {
		int choice;
		Users curUser = null;
		do {
			System.out.print("\n1. Sign Up\n2. Sign In\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 0: // Exit
				System.out.println("Bye!");
				break;
			case 1: // Sign Up	
				signUp();
				break;
			case 2: // Sign In	
				curUser = signIn();
				if(curUser != null) {
					userOperations(curUser);
				} else {
					System.out.println("User Not Found");
				}
				break;
			default:
				System.out.println("Enter valid choice");
				break;
			}
			
		}while(choice != 0);
	}
	
	public static void signUp() {
		System.out.print("First Name: ");
		String fname = sc.next();
		System.out.print("Last Name: ");
		String lname = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.println("Mobile Number : ");
		String mobile = sc.next();
		System.out.print("Birth Date (dd-MM-yyyy): ");
		String dateStr = sc.next();
		Users user = new Users(0, fname,lname,email,passwd,mobile,DateUtil.parse(dateStr));
		try(UsersDao dao = new UsersDao()) {
			int count = dao.save(user);
			System.out.println("User registered: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static Users signIn() {
		String email;
		String password;
		System.out.print("Enter email: ");
		email = sc.next();
		System.out.print("Enter password: ");
		password = sc.next();
		try(UsersDao dao = new UsersDao()) {
			Users user = dao.findByEmail(email);
			if(user != null && password.equals(user.getPassword()))
				return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void userOperations(Users curUser) {
		int choice;
		do {
			choice=mainMenu(sc);
			switch (choice) {
			case 1:
				try (UsersDao ud = new UsersDao()){
					System.out.print("First Name: ");
					String fname = sc.next();
					System.out.print("Last Name: ");
					String lname = sc.next();
					System.out.print("Email: ");
					String email = sc.next();
					System.out.print("Password: ");
					String passwd = sc.next();
					System.out.println("Mobile Number : ");
					String mobile = sc.next();
					System.out.print("Birth Date (dd-MM-yyyy): ");
					String dateStr = sc.next();
					Users user = new Users(curUser.getId(), fname,lname,email,passwd,mobile,DateUtil.parse(dateStr));
					int count = ud.changeUser(user);
					System.out.println("User updated Successfully");
					System.out.println("Rows Affected : "+count);
					choice = 0;
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try (UsersDao ud = new UsersDao()){
					System.out.println("Enter new password : ");
					String pass = sc.next();
					curUser.setPassword(pass);
					int count = ud.changePass(curUser);
					System.out.println("Password updated Successfully");
					System.out.println("Rows Affected : "+count);
					choice = 0;
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try (MoviesDao md = new MoviesDao()){
					List<Movie> list = md.findAll();
					for (Movie m:list) {
						System.out.println(m);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try (MoviesDao md = new MoviesDao()){
					List<Movie> list = md.findAll();
					for (Movie m:list) {
						System.out.println(m);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Enter movie id : ");
				int id = sc.nextInt();
				System.out.println("Enter rating : ");
				int rating = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Review : ");
				String review = sc.nextLine();
				Review r = new Review(0,id,review,rating,curUser.getId());
				try (ReviewDao md = new ReviewDao()){
					md.createReview(r, curUser);
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try (ReviewDao rd = new ReviewDao()){
					List<Review> list = rd.displayMyReviews(curUser);
					for (Review rev:list) {
						System.out.println(rev);
					}
	
					System.out.println("Enter Review id to be edited : ");
					int reviewId = sc.nextInt();
					Review r4 = rd.findReviewById(reviewId, curUser);
					if (r4==null) {
						System.out.println("Review not found");
						break;
					}
					System.out.println("Enter new rating : ");
					int nRating = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter new review : ");
					String nReview = sc.nextLine();
					Review r1 = new Review(reviewId,0,nReview,nRating,curUser.getId());
					int count = rd.updateReview(r1, curUser);
					System.out.println("Review Updated Successfully");
					System.out.println("Rows Affected : "+count);
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 6:
				try (ReviewDao rd = new ReviewDao()){
					List<Review> list = rd.displayMyReviews(curUser);
					for (Review rev:list) {
						System.out.println(rev);
					}
	
					System.out.println("Enter Review id to be deleted : ");
					int reviewId = sc.nextInt();
					
					int count = rd.deleteReview(reviewId,curUser);
					if (count!=0) {
						System.out.println("Review Deleted Successfully");
						System.out.println("Rows Affected : "+count);
					} else {
						System.out.println("Review not found");
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 7:
				try (ReviewDao rd = new ReviewDao()){
					List<Review> list = rd.displayAllReviews();
					for (Review rev:list) {
						System.out.println(rev);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 8:
				try (ReviewDao rd = new ReviewDao()){
					List<Review> list = rd.displayMyReviews(curUser);
					for (Review rev:list) {
						System.out.println(rev);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 9:
				try(ShareDao sd = new ShareDao()){
					List<Review> rlist = sd.displayReviewsSharedWithMe(curUser);
					for(Review reviw:rlist) {
						System.out.println(reviw.toString());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 10:
				try (ReviewDao rd = new ReviewDao()){
					List<Review> list = rd.displayMyReviews(curUser);
					for (Review rev:list) {
						System.out.println(rev);
					}
					System.out.println("Enter review id to be shared : ");
					int rId = sc.nextInt();
					Review r2 = rd.findReviewById(rId, curUser);
					if (r2==null) {
						System.out.println("Review not found");
						break;
					} else {
					System.out.println(r2);
					}
					try(UsersDao ud = new UsersDao()){
						List<Users> l = ud.findAll();
						for (Users u:l) {
							System.out.println(u.getId()+" "+u.getFirstName()+" "+u.getLastName()+" ("+u.getEmail()+")");
						}
					}
					List<Integer> i = new ArrayList<Integer>();
					System.out.print("Enter user ids to share review with (enter 0 to end):");
					while(true) {
						int c = sc.nextInt();
						if (c==curUser.getId()) {
							System.err.println("You cannot share review with yourself");
							break;
						}
						if (c==0) {
							break;
						}
						
						i.add(c);
					}
					
					try(ShareDao sd = new ShareDao()){
						int count = sd.shareReview(rId, i);
						System.out.println("Review shared with "+count+" users successfully");
					}
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Enter valid choice");
				break;
				
			}
		}while(choice!=0);
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		mainMenu();
		sc.close();

	}

}
