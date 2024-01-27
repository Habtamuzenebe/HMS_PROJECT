use HMS;

CREATE TABLE guests (
      guest_id INT PRIMARY KEY not null,
      first_name VARCHAR(25) ,
      last_name VARCHAR(25) ,
      email VARCHAR(30),
      phone_number VARCHAR(20)
);


CREATE TABLE bookings (
    booking_id INT PRIMARY KEY not null,
    guest_id INT,
    room_number INT,
    check_in_date DATE,
    check_out_date DATE,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id) on delete cascade,
    FOREIGN KEY (room_number) REFERENCES rooms(room_number) on delete cascade 
);


CREATE TABLE rooms (
    room_number INT PRIMARY KEY  not null,
    capacity INT,
    price_per_night DECIMAL(10, 2),
    is_booked BOOLEAN
);


INSERT INTO rooms VALUES
(101, 2, 100.00, 0),
(102, 3, 150.00, 0),
(103, 2, 120.00,  0),
(104, 4, 200.00, 0),
(105, 1, 105.00, 0),
(106, 3, 130.00, 0),
(107, 2, 110.00,0),
(108, 4, 180.00, 0),
(109, 2, 100.00,0),
(110, 3, 140.00, 0);

select *from guests;
select *from rooms;
select *from bookings;






Views





