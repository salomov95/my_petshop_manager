DELETE FROM tb_appointments WHERE 1+1;

DELETE FROM tb_users WHERE 1+1;

INSERT INTO tb_appointments (
  id,
  pet_name,
  pet_tutor_name,
  descriptor,
  due_date,
  due_time,
  contact_phone,
  status
) VALUES (
  '4ad3a58a-5d57-4800-b649-96d58c6f4923',
  'MION',
  'Salomov M.',
  'Vermifugaçao',
  '2025-01-20',
  '14:56:00',
  '(92) 9-9482-5428',
  'APPOINTMENTS.PENDING'
), (
  '65d12e17-27ae-4caa-ad74-1697abaab55b',
  'PLUTO',
  'John D.',
  'Vacinação',
  '2025-01-20',
  '13:24:00',
  '(92) 9-9542-1015',
  'APPOINTMENTS.PENDING'
), (
  '65782408-4e28-4c1f-ad3d-43c1598b95c2',
  'MUSTAFFA',
  'Jhanette D.',
  'Banho e Tosa',
  '2025-01-20',
  '09:35:00',
  '(92) 9-9422-7530',
  'APPOINTMENTS.CANCELLED'
), (
  'bfe00f2f-a5e4-4b63-8fe0-6e7e0c94705d',
  'ENTROPIA',
  'P. H. Pedro',
  'Banho e Tosa',
  '2025-01-20',
  '18:15:00',
  '(92) 9-8155-1325',
  'APPOINTMENTS.ATTENDED'
);

INSERT INTO tb_users (id, username, password)  VALUES (
  "192feaf5-7fd0-28de-3afd-18a87dadf82f",
  "salomovs95",
  "$2a$12$g.edMMvLLTODGkTavD/4GOKc/V/vS7rF4pcGfX38ycrSOFqPeVuny"
);

