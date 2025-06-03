/**
 */
package org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentee;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.Mentor;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.Person;
import org.rm2pt.sample.schoolclass.metamodel.schoolclass.SchoolclassPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl#getTeacher <em>Teacher</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl#getSupervising <em>Supervising</em>}</li>
 *   <li>{@link org.rm2pt.sample.schoolclass.metamodel.schoolclass.impl.PersonImpl#getSupervised <em>Supervised</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PersonImpl extends MinimalEObjectImpl.Container implements Person {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStudent() <em>Student</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudent()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> student;

	/**
	 * The cached value of the '{@link #getTeacher() <em>Teacher</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeacher()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> teacher;

	/**
	 * The cached value of the '{@link #getSupervising() <em>Supervising</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupervising()
	 * @generated
	 * @ordered
	 */
	protected EList<Mentor> supervising;

	/**
	 * The cached value of the '{@link #getSupervised() <em>Supervised</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupervised()
	 * @generated
	 * @ordered
	 */
	protected EList<Mentee> supervised;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolclassPackage.Literals.PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SchoolclassPackage.PERSON__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getStudent() {
		if (student == null) {
			student = new EObjectWithInverseResolvingEList.ManyInverse<Person>(Person.class, this,
					SchoolclassPackage.PERSON__STUDENT, SchoolclassPackage.PERSON__TEACHER);
		}
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getTeacher() {
		if (teacher == null) {
			teacher = new EObjectWithInverseResolvingEList.ManyInverse<Person>(Person.class, this,
					SchoolclassPackage.PERSON__TEACHER, SchoolclassPackage.PERSON__STUDENT);
		}
		return teacher;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Mentor> getSupervising() {
		if (supervising == null) {
			supervising = new EObjectResolvingEList<Mentor>(Mentor.class, this, SchoolclassPackage.PERSON__SUPERVISING);
		}
		return supervising;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Mentee> getSupervised() {
		if (supervised == null) {
			supervised = new EObjectResolvingEList<Mentee>(Mentee.class, this, SchoolclassPackage.PERSON__SUPERVISED);
		}
		return supervised;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__STUDENT:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getStudent()).basicAdd(otherEnd, msgs);
		case SchoolclassPackage.PERSON__TEACHER:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getTeacher()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__STUDENT:
			return ((InternalEList<?>) getStudent()).basicRemove(otherEnd, msgs);
		case SchoolclassPackage.PERSON__TEACHER:
			return ((InternalEList<?>) getTeacher()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__NAME:
			return getName();
		case SchoolclassPackage.PERSON__STUDENT:
			return getStudent();
		case SchoolclassPackage.PERSON__TEACHER:
			return getTeacher();
		case SchoolclassPackage.PERSON__SUPERVISING:
			return getSupervising();
		case SchoolclassPackage.PERSON__SUPERVISED:
			return getSupervised();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__NAME:
			setName((String) newValue);
			return;
		case SchoolclassPackage.PERSON__STUDENT:
			getStudent().clear();
			getStudent().addAll((Collection<? extends Person>) newValue);
			return;
		case SchoolclassPackage.PERSON__TEACHER:
			getTeacher().clear();
			getTeacher().addAll((Collection<? extends Person>) newValue);
			return;
		case SchoolclassPackage.PERSON__SUPERVISING:
			getSupervising().clear();
			getSupervising().addAll((Collection<? extends Mentor>) newValue);
			return;
		case SchoolclassPackage.PERSON__SUPERVISED:
			getSupervised().clear();
			getSupervised().addAll((Collection<? extends Mentee>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__NAME:
			setName(NAME_EDEFAULT);
			return;
		case SchoolclassPackage.PERSON__STUDENT:
			getStudent().clear();
			return;
		case SchoolclassPackage.PERSON__TEACHER:
			getTeacher().clear();
			return;
		case SchoolclassPackage.PERSON__SUPERVISING:
			getSupervising().clear();
			return;
		case SchoolclassPackage.PERSON__SUPERVISED:
			getSupervised().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case SchoolclassPackage.PERSON__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case SchoolclassPackage.PERSON__STUDENT:
			return student != null && !student.isEmpty();
		case SchoolclassPackage.PERSON__TEACHER:
			return teacher != null && !teacher.isEmpty();
		case SchoolclassPackage.PERSON__SUPERVISING:
			return supervising != null && !supervising.isEmpty();
		case SchoolclassPackage.PERSON__SUPERVISED:
			return supervised != null && !supervised.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //PersonImpl
