package th.ku.bookstore;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookDaoImp implements BookDao {
    private JdbcTemplate jdbcTemplate;


    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        //value บ่งบอกว่ารับ object
        String query = "INSERT INTO Book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                {book.getId(), book.getName(), book.getPrice()};
//        jdbcTemplate.update(query, data);

    }

    public void update(int id, Book book) {
     String query = "USDATE Book SET price = '"+book.getPrice()+"' WHERE id= '"+id+"'";
     Object[] data = new Object[]{book.getId(),book.getName(),book.getPrice()};


    }

    public void deleteById(int id) {
        String query = "DELETE FROM Book WHERE id = '"+id+"'";


    }

    public Book findById(int id) {
        String query = "SELECT * FROM Book WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> findAll() {
        // เอามาทั้งหมดเลย
        String query = "SELECT * FROM Book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;


    }

    class BookRowMapper implements RowMapper<Book> {

        public Book mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Book book = new Book(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"));
            return book;
        }
    }
}






