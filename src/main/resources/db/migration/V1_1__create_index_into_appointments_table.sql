CREATE UNIQUE INDEX no_duplicate_appoitments_index ON tb_appointments (
  pet_tutor_name,
  pet_name,
  due_date
);
