package ru.babagay.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.babagay.entity.Account;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.T;

public class DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class);

    private static final String PASSWORD_HASH = "$2a$10$q7732w6Rj3kZGhfDYSIXI.wFp.uwTSi2inB2rYHvm1iDIAf1J1eVq";

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/social";
    static final String USER = "postgres";
    static final String PASS = "root";

    static final String MEDIA_DIR = "/home/babagay/IdeaProjects/java-demo/src/main/webapp/media";
    static final String PHOTO_PATH = "/home/babagay/IdeaProjects/java-demo/external/img";
    static final String CERT_PATH = "/home/babagay/IdeaProjects/java-demo/external/certificate";

    static final String COUNTRY = "Ukraine";
    static final String CITIES[] = {"Kharkov", "Kiev", "Odessa"};
    static final String LANGS[] = {"English", "German", "Italian", "French", "Spanish"};

    static final String HOBBIES[] = {
            "Cycling", "Handball", "Football", "Basketball", "Bowling", "Boxing", "Volleyball", "Baseball", "Skating",
            "Skiing", "Table tennis", "Tennis",
            "Weightlifting", "Automobiles", "Book reading", "Cricket", "Photo", "Shopping", "Cooking",
            "Codding", "Animals", "Traveling", "Movie", "Painting", "Darts", "Fishing", "Kayak slalom",
            "Games of chance", "Ice hockey", "Roller skating", "Swimming", "Diving", "Golf", "Shooting",
            "Rowing", "Camping", "Archery", "Pubs", "Music", "Computer games", "Authorship", "Singing",
            "Foreign lang", "Billiards", "Skateboarding", "Collecting", "Badminton", "Disco",

            "Recycling", "Organic farming","Composting","Landscape gardening", "Hydroponic gardening",
            "Making birdfeeders/birdhouses","Creating art from used material", "Making recycled paper",
            "Animal care",
                    "Scuba diving",
                    "River rafting",
                    "Bungee jumping",
                    "Skiing",
                    "Trekking",
                    "Ice skating",
                    "Surfing",
                    "Racing",
                    "Gymnastics",
                    "Hunting",

                    "Painting",
                    "Graffiti art",
                    "Creative writing",
                    "Dancing/choreography",
                    "Singing/composing music",
                    "Sculpting",
                    "Model building",
                    "Interior decorating",
                    "Jewelry-making",

                    "Computer games",
                    "Video gaming",
                    "Social networking",
                    "Keeping virtual pets",
                    "Creating software",
                    "Internet browsing",
                    "Blogging",
                    "Building computers",

            "Fishing",
                    "Archery",
                    "Boating",
                    "Traveling",
                    "Camping",
                    "Kayaking",
                    "Kart racing",
                    "Golfing",
                    "Swimming",
                    "Skateboarding",
            "Playing cards",
                    "Tarot card reading",
                    "Playing board games",
                    "Watching movies",
                    "Cubing",
                    "Ping pong/table tennis",
                    "Pottery",
            "Birdwatching",
                    "Geocaching",
                    "Photography",
                    "Cloud watching",
                    "Stargazing",
                    "People watching",
                    "Herping (looking for reptiles)",
                    "Amateur meteorology",
                    "Reading",
            "Yoga",
                    "Meditation",
                    "Exercising and body building",
                    "Participating in marathons",
                    "Jumping rope",
                    "Swimming",
                    "Martial arts",
                    "Fitness counseling",
                    "Recipe creation",
            "Wine tasting",
                    "Shopping for latest trends",
                    "Food critic",
                    "Pet training and grooming",
                    "Collecting vintage items",
                    "Hosting parties",
                    "Collecting precious gems",
                    "Traveling and exploration"
    };

    public static List<String> LangTypes = new ArrayList<>();
    public static List<String> LangLevel = new ArrayList<>();

    private static final Random r;

    private List<Account> userList;

    static {
        r = new Random();

        LangTypes.add("all");
        LangTypes.add("spoken");
        LangTypes.add("writing");

        LangLevel.add("beginner");
        LangLevel.add("elementary");
        LangLevel.add("pre intermediate");
        LangLevel.add("intermediate");
        LangLevel.add("upper intermediate");
        LangLevel.add("advanced");
        LangLevel.add("proficiency");
    }


    private BufferedReader reader;

    Connection conn = null;
    Statement stmt = null;

    /**
     * TODO
     * @link github.com/nedisUa/resume/blob/8aaa4416505fdfb713794f728cd754c1a1c33525/src/dev/java/net/devstudy/resume/testenv/TestDataGenerator.java
     * @param args
     */
    public static void main(String[] args) {

        LOGGER.debug("main");

        DataGenerator generator = new DataGenerator();
        generator.init();

        try {
            generator.setUpBufferedReader("src/main/resources/users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        generator.clearDb();

        generator.accountInitFromFile();

        generator.loadPhotos();

        generator.insertAccount();

        //TODO
//        insertAccountDetails();
//        insertEducation(c);
//        insertHobbies(c);
//        insertLanguages(c);
//        insertPractics(c, profileConfig);
//        insertSkills(c, profileConfig);
//        insertCourses(c);
//        insertSkillCategories();

        try {
            generator.closeBufferedReader();
        } catch (IOException e) {
            e.printStackTrace();
        }

        generator.close();

    }

    /**
     * @Constructor
     */
    public DataGenerator() {
        userList = new ArrayList<>();
    }

    private void clearDb()  {

        try {

            Statement st = conn.createStatement();

            st.executeUpdate("delete from account");
            st.executeUpdate("delete from skill_category");
            st.executeQuery("select setval('account_seq', 1, false)");
            st.executeQuery("select setval('extra_info_seq', 1, false)");
            st.executeQuery("select setval('hobbi_seq', 1, false)");
            st.executeQuery("select setval('certificate_id_seq', 1, false)");
            st.executeQuery("select setval('education_seq', 1, false)");
            st.executeQuery("select setval('lang_seq', 1, false)");
            st.executeQuery("select setval('work_seq', 1, false)");
            st.executeQuery("select setval('skill_seq', 1, false)");
            st.executeQuery("select setval('skill_category_id_seq', 1, false)");
            st.executeQuery("select setval('extra_course_seq', 1, false)");
            st.executeQuery("select setval('image_seq', 1, false)");
            st.executeQuery("select setval('tag_seq', 1, false)");
            st.executeQuery("select setval('user_details_seq', 1, false)");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Db cleared");
    }

    // разбор фоток
    private List<Account> loadPhotos() {

        File[] photos = new File(PHOTO_PATH).listFiles();
        List<Account> list = new ArrayList<>(photos.length);

        int index = 0;

        for (File f : photos) {
            String[] names = f.getName().replace(".jpg", "").split(" ");
//            list.add(new Profile(names[0], names[1], f.getAbsolutePath()));
            userList.get(index++).setLocalPhoto(f.getName());
//            System.out.println(index + " " + f.getName());
        }


//        userList.forEach( user -> {
//            System.out.println( user.getLocalPhoto() );
//
//        });


//
//        Collections.sort(list, new Comparator<Profile>() {
//            @Override
//            public int compare(Profile o1, Profile o2) {
//                int firstNameCompare = o1.firstName.compareTo(o2.firstName);
//                if (firstNameCompare != 0) {
//                    return firstNameCompare;
//                } else {
//                    return o1.lastName.compareTo(o2.lastName);
//                }
//            }
//        });


        return list;
    }

    public void setUpBufferedReader(String fileName) throws IOException {
        reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8);
    }

    public void closeBufferedReader() throws IOException {
        reader.close();
    }

    /**
     * Load accounts to list
     */
    public void accountInitFromFile(){

        String file = "users.txt";

        final int[] t = {1};

        reader.lines().forEach( i -> {

            t[0]++;



            String a[] = i.split("\\|");

//            for (String s : a = i.split("\\|")) {
//                System.out.println(s + "--");
//            }

//            11|Boris|Kaga|boris|123456|boris@superiorpromos.com|5d57c78731cbeb4f2d1311

            String login = a[3];
            String password = a[4];
            String first_name = a[1];
            String last_name = a[2];
            String email = a[5];
            String uid = a[6];


            userList.add(new Account(login,password,first_name,last_name,email,uid));

//            System.out.println(t[0] +  "\n");



        } );



//        try (
//                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( file ) )
//        ) {
//
//
//
//        } catch (                FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void init(){

        // Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close(){

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getCountry(){

    }

    private void getRandomCity(){

    }

    private String getRandomHobbi(){

        String hobby = "";

        int e = r.nextInt(HOBBIES.length);

        hobby = HOBBIES[e];

        return hobby;
    }

    public void insertAccount(){

        String query, detailsQuery;
        String login, password, first_name, last_name, email, uid;
        boolean active = true;

        int ind = 0;
        int pKey = -1;
        long insertKey = 0;

        PreparedStatement ps = null;



        try {

            stmt = conn.createStatement();

            ps = conn.prepareStatement("INSERT INTO account (id,login,password,first_name, last_name, email, active, uid) " +
                    "VALUES (nextval('account_seq'),?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            conn.setAutoCommit(false);

            for (Account account : userList) {

                if(ind++ >= 36) break;

                ps.setString(1, account.getLogin());
                ps.setString(2, account.getPassword());
                ps.setString(3, account.getFirst_name());
                ps.setString(4, account.getLast_name());
                ps.setString(5, account.getEmail());
                ps.setBoolean(6, active);
                ps.setString(7, account.getUid());

                ps.addBatch();


//                ps.executeUpdate();

//                ResultSet rsKey = ps.getGeneratedKeys();
//
//                if(rsKey.next()) {
//
//                    insertKey = rsKey.getInt(1);
//                }

//                account.setId( insertKey );
//
//                insertHobbies( insertKey );
            }

            int[] res = ps.executeBatch();

            conn.commit();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                int id = rs.getInt(13);
                String inserted_uid = rs.getString(14);
                setAcountIndex(inserted_uid,id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setAcountIndex(String uid, long id){

        userList.stream().filter( i -> {
            return i.getUid().equals(uid);
        } ).collect(Collectors.toList()).get(0).setId(id);

    }

    public void insertHobbies(long accountId){

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO hobbi (id,account_id,title) values (nextval('hobbi_seq'),?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        List<String> hobbies = new ArrayList<>(Arrays.asList(HOBBIES));

        for (int i = 0; i < 5; i++) {
            try {
                ps.setLong(1, accountId);
                ps.setString(2, getRandomHobbi());
                ps.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
