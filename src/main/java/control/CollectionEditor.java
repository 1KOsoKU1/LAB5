package control;

import data.FormOfEducation;
import data.StudyGroup;
import exceptions.NoElementWithSuchIdException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * class for working with the collection
 */
public class CollectionEditor {
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<StudyGroup> collection;
    private ZonedDateTime initializationDate;

    /**
     * constructor is private, so there is only one instance of that class
     */
    public CollectionEditor() {
        this.initializationDate = ZonedDateTime.now();
        this.collection = ClassCollection.getList();
    }

    /**
     * cleans collection
     */
    public void cleanCollection() {
        collection.clear();
    }

    /**
     * returns all information about collection (size, initializationDate, type of collection, stored type)
     */
    public String getInformationAboutCollection() {
        String size = "";
        if (collection.isEmpty()) {
            size = "collection is empty";
        } else {
            size = String.valueOf(collection.size());
        }
        String initializationDate = DateTimeFormatter.ISO_DATE_TIME.format(this.initializationDate);
        String type = collection.getClass().getTypeName();
        String storedType = "StudyGroup";
        String res = "";
        res += "Информация о коллекции:" +
                "\nРазмер                 | " + size +
                "\nДата инициализации     | " + initializationDate +
                "\nТип коллекции          | " + type +
                "\nХронимый класс         | " + storedType +
                "\n";
        return res;
    }

    public String getCountGraterThanStudentsCount(int count) {
        Integer k = 0;
        for (StudyGroup collectionElement: collection) {
            if (collectionElement.getStudentsCount() > count) {
                k += 1;
            }
        }
        String kInString = k.toString();
        return "Колличество элементов которое больше заданного: " + kInString;
    }

    /**
     *  returns the amount of elements with a specific form of education
     */
    public String getAmountOfGroupsByFormOfEducation() {
        if (collection.isEmpty()) {
            return "коллекция пуста";
        } else {
            String res = "Колличество групп с определённой формой обучения: ";
            Integer kDistanceEd = 0;
            Integer kFullTimeEd = 0;
            Integer kEveningClasses = 0;
            for (StudyGroup collectionElement: collection) {
                if (collectionElement.getFormOfEducation().equals(FormOfEducation.DISTANCE_EDUCATION)) {
                    kDistanceEd += 1;
                } else if (collectionElement.getFormOfEducation().equals(FormOfEducation.FULL_TIME_EDUCATION)) {
                    kFullTimeEd += 1;
                } else if (collectionElement.getFormOfEducation().equals(FormOfEducation.EVENING_CLASSES)) {
                    kEveningClasses += 1;
                }
            }
           String kDistancEdInString = kDistanceEd.toString();
           String kFullTimeEdInString = kFullTimeEd.toString();
           String kEveningClassesInString = kEveningClasses.toString();
           res +=   "\nДистанционная форма обучения     | " + kDistancEdInString +
                    "\nОчная форма(днеыные занятия)     | " + kFullTimeEdInString +
                    "\nОчная форма(вечерние занятия)    | " + kEveningClassesInString +
                    "\n";
           return res;
        }
    }

    /**
     * returns Fuel type field values in descending order
     */
    public String getCollectionInDescendingOrder() {
        if (!collection.isEmpty()) {
            String res = "Коллекция в порядке убывания: \n";
            Collections.sort(collection);

            Iterator i = collection.iterator();
            while (i.hasNext()) {
                StudyGroup o1 = (StudyGroup)i.next();
               try {
                   res +=  "\nИмя                     | " + o1.getName() +
                           "\nid                      | " + o1.getId() +
                           "\nКоордината x            | " + o1.getCoordinates().getX() +
                           "\nКоордината у            | " + o1.getCoordinates().getY() +
                           "\nКоличество студентов    | " + o1.getStudentsCount() +
                           "\nПереведенные студенты   | " + o1.getTransferredStudents() +
                           "\nСеместр                 | " + o1.getSemesterEnum() +
                           "\nВремя инциализации      | " + DateTimeFormatter.ISO_DATE_TIME.format(o1.getCreationDate()) +
                           "\nАдмин группы            | " + o1.getGroupAdmin().getName() +
                           "\n--------------------------";
               } catch (IllegalArgumentException e) {
                   System.out.println("Файл с коллекцией был повреждён или изменён некорректно!");
                   System.exit(0);
               }
            }

            return res;
        } else {
            return "Коллекция пуста, выводить нечего ¯\\_(ツ)_/¯";
        }
    }

    /**
     * returns first element of collection
     */
    public String getFirstElementOfCollection() {
        if (this.collection.isEmpty()) {
            return "коллекция пуста!";
        } else {
            StudyGroup o1 = this.collection.getFirst();
            String res = "Первый элемент коллекции: ";
            try {
                res += "\nНазвание                | " + o1.getName() +
                        "\nid                      | " + o1.getId() +
                        "\nКоордината x            | " + o1.getCoordinates().getX() +
                        "\nКоордината у            | " + o1.getCoordinates().getY() +
                        "\nКоличество студентов    | " + o1.getStudentsCount() +
                        "\nПереведенные студенты   | " + o1.getTransferredStudents() +
                        "\nСеместр                 | " + o1.getSemesterEnum() +
                        "\nВремя инциализации      | " + DateTimeFormatter.ISO_DATE_TIME.format(o1.getCreationDate()) +
                        "\nАдмин группы            | " + o1.getGroupAdmin().getName() +
                        "\nДень рождения админа    | " + o1.getGroupAdmin().getBirthday() +
                        "\nЦвет глаз админа        | " + o1.getGroupAdmin().getEyeColor() +
                        "\n--------------------------";
            } catch (IllegalArgumentException e) {
                System.out.println("Файл с коллекцией был повреждён или изменён некорректно!");
                System.exit(0);
            }
            return res;
        }
    }

    /**
     * returns list of collection elements with their parameters
     */
    public String getStringInterpretationOfCollection() {
        if (this.collection.isEmpty()) {
            return "Коллекция пуста!";
        } else {
            String res = "Коллекция учебных групп: ";
            for (StudyGroup collectionElement : collection) {
              try {
                  res +=  "\nНазвание                | " + collectionElement.getName() +
                          "\nid                      | " + collectionElement.getId() +
                          "\nКоордината x            | " + collectionElement.getCoordinates().getX() +
                          "\nКоордината у            | " + collectionElement.getCoordinates().getY() +
                          "\nКоличество студентов    | " + collectionElement.getStudentsCount() +
                          "\nПереведенные студенты   | " + collectionElement.getTransferredStudents() +
                          "\nСеместр                 | " + collectionElement.getSemesterEnum() +
                          "\nВремя инциализации      | " + DateTimeFormatter.ISO_DATE_TIME.format(collectionElement.getCreationDate()) +
                          "\nАдмин группы            | " + collectionElement.getGroupAdmin().getName() +
                          "\nДень рождения админа    | " + collectionElement.getGroupAdmin().getBirthday() +
                          "\nЦвет глаз админа        | " + collectionElement.getGroupAdmin().getEyeColor() +
                          "\n--------------------------";


              } catch (IllegalArgumentException e) {
                  System.out.println("Файл с коллекцией повреждён или изменнён некорректно!");
                  System.exit(0);
              }
            }
            return res;
        }
    }

    /**
     * removes all elements with id greater that the one you specified
     *
     * @param studentsCount of the element, all elements greater than which you want to remove
     * @return true if collection changed after operation
     */
    public boolean removeElementsWithGreaterStudentsCount(int studentsCount) {
        boolean haveElementWithGreaterStudentsCount = false;
        LinkedList<StudyGroup> elementsForDeleting = new LinkedList<>();
        for (StudyGroup element : collection) {
            if (element.getStudentsCount() > studentsCount) {
                elementsForDeleting.add(element);
                haveElementWithGreaterStudentsCount = true;
            }
        }
        for (StudyGroup studyGroup : elementsForDeleting) {
            collection.remove(studyGroup);
        }
        return haveElementWithGreaterStudentsCount;
    }

    /**
     * returns element with specified id
     *
     * @param id of the element you want to get
     * @throws NoElementWithSuchIdException if theres no elements in the
     *                                      collection with the id of entered element
     */
    public StudyGroup getStudyGroupById(int id) throws NoElementWithSuchIdException {
        for (StudyGroup collectionElement : collection) {
           try {
               if (collectionElement.getId() == id) {
                   return collectionElement;
           }
            }catch (IllegalArgumentException e) {
               System.out.println("Файл с коллекцией повреждён или изменён некорректно!");
           }
        }
        throw new NoElementWithSuchIdException();
    }

    public void addIfMax(int number) {
        if (!collection.isEmpty()) { ;
            StudyGroup maxElement = collection.getFirst();
            for (StudyGroup collectionElement: collection) {
                if (maxElement.compareTo(collectionElement) == -1) {
                    maxElement = collectionElement;
                }
            }
            if (number > maxElement.getStudentsCount()) {
                StudyGroup addElement = new StudyGroup(getMaxIdOfCollection() + 1, System.out, scanner, number);
                collection.add(addElement);
            }
            } else {
                System.out.println("Коллекция пуста!");
            }
        }


    /**
     * returns the biggest id in the collection or return 0 if there is no element
     *
     * @return max id of the collection
     */
    public int getMaxIdOfCollection() {
        int maxId = 0;
        for (StudyGroup collectionElement : collection) {
            if (collectionElement.getId() > maxId) {
                maxId = collectionElement.getId();
            }
        }
        return maxId;
    }

    /**
     *
     * @return max studentsCount in the collection
     */
    public int getMaxStudentsCountInTheCollection() {
        int maxStudentsCount = 0;
        for (StudyGroup collectionElement : collection) {
            if (collectionElement.getStudentsCount() > maxStudentsCount) {
                maxStudentsCount = collectionElement.getStudentsCount();
            }
        }
        return maxStudentsCount;
    }

    /**
     * adds vehicle to the collection
     *
     * @param studyGroup you want to add to the collection
     */
    public void addElement(StudyGroup studyGroup) {
        collection.add(studyGroup);
    }

//    public String addElementIfMax(StudyGroup elementToAdd) {
//        int maxStudentsCount = 0;
//        for (StudyGroup collectionElement : collection) {
//            if (collectionElement.getStudentsCount() > maxStudentsCount) {
//                maxStudentsCount = collectionElement.getStudentsCount();
//            }
//        }
//        if (elementToAdd.getStudentsCount() > maxStudentsCount) {
//            collection.add(elementToAdd);
//        }
//    }

    /**
     * says whether collection is empty or not
     *
     * @return boolean
     */
    public boolean isCollectionEmpty() {
        return collection.isEmpty();
    }

    /**
     * returns collection
     *
     * @return collection
     */
    public Collection<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * replace existing element with some id with another with the same id
     *
     * @param element that we want to put instead of existing
     * @throws NoElementWithSuchIdException if theres no elements in the
     *                                      collection with the id of entered element
     */
    public void updateElement(StudyGroup element) throws NoElementWithSuchIdException {
        int elementId = element.getId();
        int elementIndex = getElementIndexById(elementId);
        collection.set(elementIndex, element);
    }



    /**
     *  says whether collection have element with specified id or not
     *
     * @param id integer value
     * @return boolean
     */
    public boolean isThereAnElementWithSuchId(int id) {
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * gives you an element by it index (or throws an exception at you
     * @param id of the element
     * @return int value
     * @throws NoElementWithSuchIdException if element not found
     */
    private int getElementIndexById(int id) throws NoElementWithSuchIdException {
        int index = 0;
        for (StudyGroup collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return index;
            }
            index++;
        }
        throw new NoElementWithSuchIdException();
    }

}



