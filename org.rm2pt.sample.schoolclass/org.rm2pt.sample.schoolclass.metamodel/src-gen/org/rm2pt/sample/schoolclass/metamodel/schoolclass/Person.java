/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getName <em>Name</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getStudent <em>Student</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getTeacher <em>Teacher</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervising <em>Supervising</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getSupervised <em>Supervised</em>}</li>
 * </ul>
 *
 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson()
 * @model abstract="true"
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Student</b></em>' reference list.
	 * The list contents are of type {@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person}.
	 * It is bidirectional and its opposite is '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getTeacher <em>Teacher</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student</em>' reference list.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson_Student()
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getTeacher
	 * @model opposite="Teacher" upper="50"
	 * @generated
	 */
	EList<Person> getStudent();

	/**
	 * Returns the value of the '<em><b>Teacher</b></em>' reference list.
	 * The list contents are of type {@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person}.
	 * It is bidirectional and its opposite is '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teacher</em>' reference list.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson_Teacher()
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person#getStudent
	 * @model opposite="Student" upper="7"
	 * @generated
	 */
	EList<Person> getTeacher();

	/**
	 * Returns the value of the '<em><b>Supervising</b></em>' reference list.
	 * The list contents are of type {@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supervising</em>' reference list.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson_Supervising()
	 * @model derived="true"
	 * @generated
	 */
	EList<Mentor> getSupervising();

	/**
	 * Returns the value of the '<em><b>Supervised</b></em>' reference list.
	 * The list contents are of type {@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supervised</em>' reference list.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getPerson_Supervised()
	 * @model derived="true"
	 * @generated
	 */
	EList<Mentee> getSupervised();

} // Person
