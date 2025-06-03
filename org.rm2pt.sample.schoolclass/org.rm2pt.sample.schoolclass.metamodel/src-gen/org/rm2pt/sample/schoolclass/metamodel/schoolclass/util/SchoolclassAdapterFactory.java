/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.rm2pt.sample.schoolclass.metamodel.schoolclass.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage
 * @generated
 */
public class SchoolclassAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SchoolclassPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchoolclassAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SchoolclassPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SchoolclassSwitch<Adapter> modelSwitch = new SchoolclassSwitch<Adapter>() {
		@Override
		public Adapter caseSchoolClass(SchoolClass object) {
			return createSchoolClassAdapter();
		}

		@Override
		public Adapter casePerson(Person object) {
			return createPersonAdapter();
		}

		@Override
		public Adapter caseMentor(Mentor object) {
			return createMentorAdapter();
		}

		@Override
		public Adapter caseMentee(Mentee object) {
			return createMenteeAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass <em>School Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolClass
	 * @generated
	 */
	public Adapter createSchoolClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person
	 * @generated
	 */
	public Adapter createPersonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor <em>Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor
	 * @generated
	 */
	public Adapter createMentorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee <em>Mentee</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee
	 * @generated
	 */
	public Adapter createMenteeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SchoolclassAdapterFactory
