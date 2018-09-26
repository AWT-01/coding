package org.fundacionjala.coding.Movies;

import org.fundacionjala.coding.KevinHerrera.Movies.Customer.Customer;
import org.fundacionjala.coding.KevinHerrera.Movies.Movie.Movie;
import org.fundacionjala.coding.KevinHerrera.Movies.Movie.MovieFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kevin Herrera - AWT-[01].
 * @version 0.1
 */
public class TestCustomer {
    static final String REGULAR_MOVIE_TITLE = "Batman";
    static final String NEW_RELEASE_MOVIE_TITLE = "Avengers";
    static final String CHILDREN_MOVIE_TITLE = "Little Pig";

    static final int DAYS_RENT_1 = 1;
    static final int DAYS_RENT_2 = 2;
    static final int DAYS_RENT_3 = 3;
    static final int DAYS_RENT_5 = 5;
    static final int DAYS_RENT_6 = 6;
    static final int DAYS_RENT_10 = 10;

    static final int RENT_AMOUNT_4 = 4;
    static final int RENT_AMOUNT_10 = 10;
    static final int RENT_AMOUNT_15 = 15;

    static final int RENT_POINTS_2 = 2;
    static final int RENT_POINTS_3 = 3;
    static final int RENT_POINTS_4 = 4;
    /**
     * .
     */
    @Test
    public void testCustomerName() {
        Customer customerTest = new Customer("Juan");
        assertEquals("Juan", customerTest.getName());
    }

    /**
     * .
     */
    @Test
    public void testCustomerRentalMovieBatmanFor2Days() {
        Customer customerTest = new Customer("test");
        Movie movie = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        customerTest.rentMovie(movie, DAYS_RENT_2);
        assertEquals("Batman", customerTest.getRentedMoviesList().get(0).getMovie().getTitle());
        assertEquals(DAYS_RENT_2, customerTest.getRentedMoviesList().get(0).getDaysRented());
    }

    /**
     * .
     */
    @Test
    public void testCustomerGetRentMoviesTitles() {
        Customer customerTest = new Customer("test");
        Movie movie1 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        customerTest.rentMovie(movie1, DAYS_RENT_1);
        Movie movie2 = MovieFactory.createRegularMovie("Superman");
        customerTest.rentMovie(movie2, DAYS_RENT_1);
        Movie movie3 = MovieFactory.createRegularMovie("Iron Man");
        customerTest.rentMovie(movie3, DAYS_RENT_1);
        assertEquals("Batman", customerTest.getRentedMoviesList().get(0).getMovie().getTitle());
        assertEquals("Superman", customerTest.getRentedMoviesList().get(1).getMovie().getTitle());
        assertEquals("Iron Man", customerTest.getRentedMoviesList().get(2).getMovie().getTitle());
    }

    /**
     * .
     */
    @Test
    public void testRent2DaysGetRentAmountForRegularMovie() {
        Customer customerTest = new Customer("juan");
        Movie regularMovie = MovieFactory.createRegularMovie("Batman");
        customerTest.rentMovie(regularMovie, DAYS_RENT_2);
        //rent regular: 2 per day, if(daysRented>2)--> 1.5 per day;
        //(2*2) = 4
        assertEquals(RENT_AMOUNT_4, customerTest.getTotalBillAmount());
    }

    /**
     * .
     */
    @Test
    public void testRent6DaysGetRentAmountForRegularMovie() {
        Customer customerTest = new Customer("juan");
        Movie regularMovie = MovieFactory.createRegularMovie("Batman");
        customerTest.rentMovie(regularMovie, DAYS_RENT_6);
        //rent regular: 2 per day, if(daysRented>2)-->  1.5 per day;
        //(2*2) = 4 + (4*1.5) = 10
        assertEquals(RENT_AMOUNT_10, customerTest.getTotalBillAmount());
    }

    /**
     * .
     */
    @Test
    public void test5DaysGetRentAmountForNewReleaseMovie() {
        Customer customerTest = new Customer("juan");
        Movie newMovie = MovieFactory.createNewReleaseMovie("Batman");
        //rent new release: DaysRented * 3 ;
        customerTest.rentMovie(newMovie, DAYS_RENT_5);
        //(5*3) = 15
        assertEquals(RENT_AMOUNT_15, customerTest.getTotalBillAmount());
    }

    /**
     * .
     */
    @Test
    public void test10DaysGetRentAmountForChildrenMovie() {
        Customer customerTest = new Customer("juan");
        Movie childMovie = MovieFactory.createChildrenMovie(CHILDREN_MOVIE_TITLE);
        //rent children: 1.5 per day;
        customerTest.rentMovie(childMovie, DAYS_RENT_10);
        //(10*1.5) = 15
        assertEquals(RENT_AMOUNT_15, customerTest.getTotalBillAmount());
    }

    /**
     * .
     */
    @Test
    public void testRent3RegularMovies5DaysGetFrequentRenterPoints() {
        Customer customerTest = new Customer("juan");
        Movie newMovie1 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        Movie newMovie2 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        Movie newMovie3 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        customerTest.rentMovie(newMovie1, DAYS_RENT_5);
        customerTest.rentMovie(newMovie2, DAYS_RENT_5);
        customerTest.rentMovie(newMovie3, DAYS_RENT_5);
        //1 point for movie rented, 1 point more for >2 days new release rent
        assertEquals(RENT_POINTS_3, customerTest.getFrequentRenterPoints());
    }

    /**.
     *
     */
    @Test
    public void testRent2RegularMovies1NewRelease3DaysGetFrequentRenterPoints() {
        Customer customerTest = new Customer("juan");
        Movie newMovie1 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        Movie newMovie2 = MovieFactory.createRegularMovie(REGULAR_MOVIE_TITLE);
        Movie newMovie3 = MovieFactory.createNewReleaseMovie(NEW_RELEASE_MOVIE_TITLE);
        customerTest.rentMovie(newMovie1, DAYS_RENT_3);
        customerTest.rentMovie(newMovie2, DAYS_RENT_3);
        customerTest.rentMovie(newMovie3, DAYS_RENT_3);
        //1 point for movie rented, 1 point more for >2 days new release rent
        assertEquals(RENT_POINTS_4, customerTest.getFrequentRenterPoints());
    }

    /**.
     *
     */
    @Test
    public void testRentNewRelease5DaysGetFrequentRenterPoints() {
        Customer customerTest = new Customer("juan");
        Movie newMovie = MovieFactory.createNewReleaseMovie(NEW_RELEASE_MOVIE_TITLE);
        customerTest.rentMovie(newMovie, DAYS_RENT_5);
        //1 point for movie rented, 1 point more for >2 days new release rent
        assertEquals(RENT_POINTS_2, customerTest.getFrequentRenterPoints());
    }

    /**
     * .
     */
    @Test
    public void testStatement() {
        Customer customerTest = new Customer("JOSE");
        Movie movieRevenant = MovieFactory.createNewReleaseMovie("The Revenant");
        Movie movieTerminator = MovieFactory.createRegularMovie("Terminator");
        customerTest.rentMovie(movieRevenant, 2);
        customerTest.rentMovie(movieTerminator, 2);
        String statementExpected = "RentalMovie Record for: JOSE"
                + System.lineSeparator()
                + "Title: The Revenant(NEW RELEASE)||\tRent Amount: 6 $||\tDays Rented: 2 day(s)"
                + System.lineSeparator()
                + "Title: Terminator(REGULAR)||\tRent Amount: 4 $||\tDays Rented: 2 day(s)"
                + System.lineSeparator()
                + "Amount owned is 10$" + System.lineSeparator()
                + "You earned:3 frequent renter points";
        assertEquals(statementExpected, customerTest.statement());
    }

}
