package data;

import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class StudyGroup extends IdHolder implements Comparable<StudyGroup>{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(int id, String name, float xCoordinate, Long yCoordinate, int studentsCount, Integer transferredStudents, FormOfEducation formOfEducation, Semester semesterEnum, String adminName, LocalDateTime bd, Color eyeColor, Country nationality, Long adminXCoordinate, Integer adminYCoordinate, int adminZCoordinate, String locationName) {
        super(id);
        this.name = name;
        coordinates = new Coordinates(xCoordinate, yCoordinate);
        this.setCreationDate(ZonedDateTime.now());
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        groupAdmin = new Person(adminName, bd, eyeColor, nationality, new Location(adminXCoordinate, adminYCoordinate, adminZCoordinate, locationName));
    }

    public StudyGroup(int id, PrintStream out, Scanner scanner){
        super(id);
        LocalDateTime bd = LocalDate.of(2020, 4, 18).atStartOfDay();
        this.coordinates = new Coordinates(0, 0L);
        this.groupAdmin = new Person("name", bd  , Color.RED, Country.UNITED_KINGDOM, new Location(0L,0,0, "ol"));
        setNameUsingUserInput(out,scanner);
        setXCoordinateUsingUserInput(out, scanner);
        setYCoordinateUsingUserInput(out, scanner);
        setStudentsCountUsingUserInput(out, scanner);
        setTransferredStudentsUsingUserInput(out, scanner);
        setFormOfEducationUsingUserInput(out, scanner);
        setSemesterEnumUsingUserInput(out, scanner);
        setAdminNameUsingUserInput(out, scanner);
        setAdminBirthdayUsingUserInput(out, scanner);
        setAdminEyeColorUsingUserInput(out,scanner);
        setAdminCountryUsingUserInput(out, scanner);
        setAdminXCoordinateUsingUserInput(out, scanner);
        setAdminYCoordinateUsingUserInput(out, scanner);
        setAdminZCoordinateUsingUserInput(out, scanner);
        setAdminLocationNameUsingUserInput(out, scanner);
        this.setCreationDate(ZonedDateTime.now());
    }

    public StudyGroup(int id, PrintStream out, Scanner scanner, int studentsCount){
        super(id);
        this.setCreationDate(ZonedDateTime.now());
        LocalDateTime bd = LocalDate.of(2020, 4, 18).atStartOfDay();
        this.coordinates = new Coordinates(0, 0L);
        this.groupAdmin = new Person("name", bd  , Color.RED, Country.UNITED_KINGDOM, new Location(0L,0,0, "ol"));
        setNameUsingUserInput(out,scanner);
        setXCoordinateUsingUserInput(out, scanner);
        setYCoordinateUsingUserInput(out, scanner);
        this.studentsCount = studentsCount;
        setTransferredStudentsUsingUserInput(out, scanner);
        setFormOfEducationUsingUserInput(out, scanner);
        setSemesterEnumUsingUserInput(out, scanner);
        setAdminNameUsingUserInput(out, scanner);
        setAdminBirthdayUsingUserInput(out, scanner);
        setAdminEyeColorUsingUserInput(out,scanner);
        setAdminCountryUsingUserInput(out, scanner);
        setAdminXCoordinateUsingUserInput(out, scanner);
        setAdminYCoordinateUsingUserInput(out, scanner);
        setAdminZCoordinateUsingUserInput(out, scanner);
        setAdminLocationNameUsingUserInput(out, scanner);
        this.setCreationDate(ZonedDateTime.now());
    }

    private void setAdminLocationNameUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            try {
                out.print("Введите место нахождения админа: ");
                String locationNameInString = scanner.nextLine();
                this.groupAdmin.setLocationName(locationNameInString);
                break;
            } catch (IllegalArgumentException e) {
                if (this.groupAdmin.getLocationName().length() == 0) {
                    this.groupAdmin.setLocationName(null);
                }
            }
        }
    }

    private void setAdminZCoordinateUsingUserInput(PrintStream out, Scanner scanner) {
        while (true){
            String adminCoordinateZInString = "";
            out.print("z(Админ): ");
            adminCoordinateZInString = scanner.nextLine().trim();
            int adminCoordinateZ = Integer.parseInt(adminCoordinateZInString);
            this.groupAdmin.setZLocation(adminCoordinateZ);
            break;
        }
    }

    private void setAdminYCoordinateUsingUserInput(PrintStream out, Scanner scanner) {
        while (true){
            String adminCoordinateYInString = "";
            try {
                out.print("y(Админ): ");
                adminCoordinateYInString = scanner.nextLine();
                Integer adminCoordinateY = Integer.parseInt(adminCoordinateYInString);
                this.groupAdmin.setYLocation(adminCoordinateY);
                break;
            } catch (NumberFormatException e) {
                if (adminCoordinateYInString.length() == 0) {
                    System.out.println("Значение координаты не было введено!");
                }
            }
        }
    }

    private void setAdminXCoordinateUsingUserInput(PrintStream out, Scanner scanner) {
        while (true){
            String adminCoordinateXInString = "";
            try {
                out.print("x(Админ): ");
                adminCoordinateXInString = scanner.nextLine();
                Long adminCoordinateX = Long.parseLong(adminCoordinateXInString);
                this.groupAdmin.setXLocation(adminCoordinateX);
                break;
            } catch (NumberFormatException e) {
                if (adminCoordinateXInString.length() == 0) {
                    System.out.println("Значение координаты не было введено!");
                }
            }
        }
    }

    private void setAdminCountryUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String adminCountryInString = "";
            try {
                String countryTip = getEnumTip(Country.class.getEnumConstants());
                out.print("Введите страну в котрой живёт админ (" + countryTip + "): ");
                adminCountryInString = scanner.nextLine();
                Country type = Country.valueOf(adminCountryInString.toUpperCase());
                this.groupAdmin.setNationality(type);
                break;
            } catch (IllegalArgumentException e) {
                if (adminCountryInString.length() == 0) {
                    this.groupAdmin.setNationality(null);
                    break;
                } else {
                    out.println("Неверно указана страна \"" + adminCountryInString + "\"");
                }
            }
        }
    }

    private void setAdminEyeColorUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String adminEyeColorInString = "";
            try {
                String ColorTip = getEnumTip(Color.class.getEnumConstants());
                out.print("Введите цвет глаз админа (" + ColorTip + "): ");
                adminEyeColorInString = scanner.nextLine();
                Color type = Color.valueOf(adminEyeColorInString.toUpperCase());
                this.groupAdmin.setEyeColor(type);
                break;
            } catch (IllegalArgumentException e) {
                if (adminEyeColorInString.trim().equals("")) {
                    this.groupAdmin.setEyeColor(null);
                    break;
                } else {
                    out.println(("указан неверный цвет глаз \"" + adminEyeColorInString + "\""));
                }
            }
        }
    }

    private void setAdminBirthdayUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            try {
                out.println("День рождения админа: ");
                out.print("год: ");
                String yearInString = scanner.nextLine();
                out.print("месяц: ");
                String monthInString = scanner.nextLine();
                out.print("день: ");
                String dayInString = scanner.nextLine();
                int year = Integer.parseInt(yearInString);
                int month = Integer.parseInt(monthInString);
                int day = Integer.parseInt(dayInString);
                LocalDateTime s = LocalDate.of(year, month, day).atStartOfDay();
                this.groupAdmin.setBirthday(s);
                if (s == null) {
                    continue;
                }
                break;
            } catch (NumberFormatException | DateTimeException e) {
                out.println("Было введено не число или не верно ведено значение даты!");
            }
        }
    }

    private void setAdminNameUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            out.print("Введите имя админа группы: ");
            this.groupAdmin.setName(scanner.nextLine());
            this.groupAdmin.setName(this.groupAdmin.getName().trim());
            if (this.groupAdmin.getName().length() == 0) {
                out.println("Имя не было введено!");
                continue;
            }
            break;
        }
    }

    private void setSemesterEnumUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String semesterEnumInString = "";
            try {
                String semesterEnumTip = getEnumTip(Semester.class.getEnumConstants());
                out.print("Введите семестр (" + semesterEnumTip + "): ");
                semesterEnumInString = scanner.nextLine();
                Semester type = Semester.valueOf(semesterEnumInString.toUpperCase());
                setSemesterEnum(type);
                break;
            } catch (IllegalArgumentException e) {
                out.println("Неверная запись семестра \"" + semesterEnumInString + "\""); //а почему я в catch не могу использовать variable из try?????
            }
        }

    }

    private void setFormOfEducationUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String formOfEducationInString = "";
            try {
                String formOfEducationTip = getEnumTip(FormOfEducation.class.getEnumConstants());
                out.print("Введите формат обучения (" + formOfEducationTip + "): ");
                formOfEducationInString = scanner.nextLine();
                FormOfEducation type =FormOfEducation.valueOf(formOfEducationInString.toUpperCase());
                setFormOfEducation(type);
                break;
            } catch (IllegalArgumentException e) {
                    out.println("Неверный формат обучения \"" + formOfEducationInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
        }

    private void setTransferredStudentsUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String transferredStudentsInString = "";
            try {
                out.print("Количество преведенных студентов: ");
                transferredStudentsInString = scanner.nextLine();
                this.transferredStudents = Integer.parseInt(transferredStudentsInString);
                if (this.transferredStudents <= 0) {
                    out.println("Ведённое значение меньше или равно 0!");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                if (transferredStudentsInString.length() == 0 | transferredStudentsInString == "") {
                    out.println("Значение небыло введено!");
                    continue;
                } else {
                    out.println("Неверный формат числа!");
                }
            }
        }
    }

    private void setStudentsCountUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String studentsCountInString = "";
            try {
                out.print("Количество студентов: ");
                studentsCountInString = scanner.nextLine();
                int studentsCountInput = Integer.parseInt(studentsCountInString);
                this.studentsCount = studentsCountInput;
                if (this.studentsCount <= 0) {
                    System.out.println("Количество не может быть меньше 0 или равнятся ему!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                if (studentsCountInString.length() == 0) {
                    System.out.println("Количество не было введено!");
                } else {
                    out.println("Не правильный формат числа \"" + studentsCountInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
        }

    }

    private void setYCoordinateUsingUserInput(PrintStream out, Scanner scanner) {
        while (true){
            String coordinateYInString = "";
            try {
                out.print("y: ");
                coordinateYInString = scanner.nextLine();
                Long coordinateY = Long.parseLong(coordinateYInString);
                this.coordinates.setY(coordinateY);
                if (this.coordinates.getY() > 710) {
                    System.out.println("Координата y не может быть больше 710!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                if (coordinateYInString.length() == 0) {
                    System.out.println("Значение координаты не было введено!");
                } else {
                    out.println("Не правильный формат числа \"" + coordinateYInString + "\"");
                }
            }
        }
    }

    private void setXCoordinateUsingUserInput(PrintStream out, Scanner scanner) {
        while (true) {
            String coordinateXInString = "";
            try {
                out.print("x: ");
                coordinateXInString = scanner.nextLine();
                float coordinateX = Float.parseFloat(coordinateXInString);
                this.coordinates.setX(coordinateX);
                break;
            } catch (NumberFormatException e) {
                if (coordinateXInString.length() == 0) {
                    out.println("Вы не ввели значение координаты");
                } else {
                    out.println("Не правильный формат числа \"" + coordinateXInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
        }
    }

    private void setNameUsingUserInput(PrintStream out, Scanner scanner) {
        while(true){
            out.print("Имя группы: ");
            this.name = scanner.nextLine();
            this.name = this.name.trim();
            if (name.length() == 0 || name.trim().equals("")){
                out.println("Имя не было введено!");
                continue;
            }
            break;
        }
    }

    private String getEnumTip(Enum[] constants) {
        return Arrays.stream(constants).map(Enum::toString).reduce((a, b) -> a + " | " + b).get();
    }

    @Override
    public int compareTo(StudyGroup o) {
        if (this.getStudentsCount() > o.getStudentsCount()) {
            return -1;
        } else if (this.getStudentsCount() < o.getStudentsCount()) {
            return 1;
        } else {
            return 0;
        }

    }

    public ZonedDateTime getCreationDate() {
        return ZonedDateTime.parse(creationDate);
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = DateTimeFormatter.ISO_DATE_TIME.format(creationDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    public void setTransferredStudents(Integer transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }
}
