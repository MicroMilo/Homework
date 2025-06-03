/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl;

import org.eclipse.emf.ecore.EClass;

import org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mentor</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class MentorImpl extends PersonImpl implements Mentor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MentorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolclassPackage.Literals.MENTOR;
	}

} //MentorImpl
