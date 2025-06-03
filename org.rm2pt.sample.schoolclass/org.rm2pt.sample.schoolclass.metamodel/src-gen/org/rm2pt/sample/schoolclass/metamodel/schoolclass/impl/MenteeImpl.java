/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl;

import org.eclipse.emf.ecore.EClass;

import org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mentee</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class MenteeImpl extends PersonImpl implements Mentee {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MenteeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolclassPackage.Literals.MENTEE;
	}

} //MenteeImpl
