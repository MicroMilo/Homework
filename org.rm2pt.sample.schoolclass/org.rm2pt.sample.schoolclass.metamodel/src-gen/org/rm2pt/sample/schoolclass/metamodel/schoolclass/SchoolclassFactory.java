/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage
 * @generated
 */
public interface SchoolclassFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SchoolclassFactory eINSTANCE = org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.SchoolclassFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>School Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>School Class</em>'.
	 * @generated
	 */
	SchoolClass createSchoolClass();

	/**
	 * Returns a new object of class '<em>Mentor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mentor</em>'.
	 * @generated
	 */
	Mentor createMentor();

	/**
	 * Returns a new object of class '<em>Mentee</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mentee</em>'.
	 * @generated
	 */
	Mentee createMentee();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SchoolclassPackage getSchoolclassPackage();

} //SchoolclassFactory
