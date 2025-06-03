/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>School Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getName <em>Name</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getSchoolClass()
 * @model
 * @generated
 */
public interface SchoolClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getSchoolClass_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage#getSchoolClass_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Person> getMembers();

} // SchoolClass
