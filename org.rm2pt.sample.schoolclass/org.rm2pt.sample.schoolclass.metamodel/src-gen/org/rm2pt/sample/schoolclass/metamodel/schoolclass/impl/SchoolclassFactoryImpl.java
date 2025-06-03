/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.rm2pt.sample.schoolclass.metamodel.schoolclass.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SchoolclassFactoryImpl extends EFactoryImpl implements SchoolclassFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SchoolclassFactory init() {
		try {
			SchoolclassFactory theSchoolclassFactory = (SchoolclassFactory) EPackage.Registry.INSTANCE
					.getEFactory(SchoolclassPackage.eNS_URI);
			if (theSchoolclassFactory != null) {
				return theSchoolclassFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SchoolclassFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolclassFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case SchoolclassPackage.SCHOOL_CLASS:
			return createSchoolClass();
		case SchoolclassPackage.MENTOR:
			return createMentor();
		case SchoolclassPackage.MENTEE:
			return createMentee();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolClass createSchoolClass() {
		SchoolClassImpl schoolClass = new SchoolClassImpl();
		return schoolClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mentor createMentor() {
		MentorImpl mentor = new MentorImpl();
		return mentor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mentee createMentee() {
		MenteeImpl mentee = new MenteeImpl();
		return mentee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolclassPackage getSchoolclassPackage() {
		return (SchoolclassPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SchoolclassPackage getPackage() {
		return SchoolclassPackage.eINSTANCE;
	}

} //SchoolclassFactoryImpl
