/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassFactory
 * @model kind="package"
 * @generated
 */
public interface SchoolclassPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "schoolclass";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.rm2pt.com/schoolclass";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "schoolclass";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SchoolclassPackage eINSTANCE = org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolClassImpl <em>School Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolClassImpl
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getSchoolClass()
	 * @generated
	 */
	int SCHOOL_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHOOL_CLASS__NAME = 0;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHOOL_CLASS__MEMBERS = 1;

	/**
	 * The number of structural features of the '<em>School Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHOOL_CLASS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>School Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHOOL_CLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__NAME = 0;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__STUDENT = 1;

	/**
	 * The feature id for the '<em><b>Teacher</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__TEACHER = 2;

	/**
	 * The feature id for the '<em><b>Supervising</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SUPERVISING = 3;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__SUPERVISED = 4;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MentorImpl <em>Mentor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MentorImpl
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getMentor()
	 * @generated
	 */
	int MENTOR = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR__STUDENT = PERSON__STUDENT;

	/**
	 * The feature id for the '<em><b>Teacher</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR__TEACHER = PERSON__TEACHER;

	/**
	 * The feature id for the '<em><b>Supervising</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR__SUPERVISING = PERSON__SUPERVISING;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR__SUPERVISED = PERSON__SUPERVISED;

	/**
	 * The number of structural features of the '<em>Mentor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Mentor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTOR_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MenteeImpl <em>Mentee</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MenteeImpl
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getMentee()
	 * @generated
	 */
	int MENTEE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE__STUDENT = PERSON__STUDENT;

	/**
	 * The feature id for the '<em><b>Teacher</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE__TEACHER = PERSON__TEACHER;

	/**
	 * The feature id for the '<em><b>Supervising</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE__SUPERVISING = PERSON__SUPERVISING;

	/**
	 * The feature id for the '<em><b>Supervised</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE__SUPERVISED = PERSON__SUPERVISED;

	/**
	 * The number of structural features of the '<em>Mentee</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Mentee</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENTEE_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass <em>School Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>School Class</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass
	 * @generated
	 */
	EClass getSchoolClass();

	/**
	 * Returns the meta object for the attribute '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getName()
	 * @see #getSchoolClass()
	 * @generated
	 */
	EAttribute getSchoolClass_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getMembers()
	 * @see #getSchoolClass()
	 * @generated
	 */
	EReference getSchoolClass_Members();

	/**
	 * Returns the meta object for class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Student</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getStudent()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Student();

	/**
	 * Returns the meta object for the reference list '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getTeacher <em>Teacher</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Teacher</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getTeacher()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Teacher();

	/**
	 * Returns the meta object for the reference list '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervising <em>Supervising</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supervising</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervising()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Supervising();

	/**
	 * Returns the meta object for the reference list '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervised <em>Supervised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supervised</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervised()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Supervised();

	/**
	 * Returns the meta object for class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor <em>Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mentor</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor
	 * @generated
	 */
	EClass getMentor();

	/**
	 * Returns the meta object for class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee <em>Mentee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mentee</em>'.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee
	 * @generated
	 */
	EClass getMentee();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SchoolclassFactory getSchoolclassFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolClassImpl <em>School Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolClassImpl
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getSchoolClass()
		 * @generated
		 */
		EClass SCHOOL_CLASS = eINSTANCE.getSchoolClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHOOL_CLASS__NAME = eINSTANCE.getSchoolClass_Name();

		/**
		 * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHOOL_CLASS__MEMBERS = eINSTANCE.getSchoolClass_Members();

		/**
		 * The meta object literal for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__STUDENT = eINSTANCE.getPerson_Student();

		/**
		 * The meta object literal for the '<em><b>Teacher</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__TEACHER = eINSTANCE.getPerson_Teacher();

		/**
		 * The meta object literal for the '<em><b>Supervising</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__SUPERVISING = eINSTANCE.getPerson_Supervising();

		/**
		 * The meta object literal for the '<em><b>Supervised</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__SUPERVISED = eINSTANCE.getPerson_Supervised();

		/**
		 * The meta object literal for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MentorImpl <em>Mentor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MentorImpl
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getMentor()
		 * @generated
		 */
		EClass MENTOR = eINSTANCE.getMentor();

		/**
		 * The meta object literal for the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MenteeImpl <em>Mentee</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.MenteeImpl
		 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassPackageImpl#getMentee()
		 * @generated
		 */
		EClass MENTEE = eINSTANCE.getMentee();

	}

} //SchoolclassPackage
