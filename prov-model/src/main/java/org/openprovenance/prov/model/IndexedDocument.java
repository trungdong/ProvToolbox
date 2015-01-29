package org.openprovenance.prov.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;


/** This class provides a set of indexes over information contained in
 * an Document, facilitating its navigation.  Its constructor takes an
 * Document builds an index for it.  Of course, for the index to be
 * maintained, one cannot access, say the list of edges, and mutate
 * it. Instead, one has to use the add methods provided.
 *<p>
 * Note that code is not thread-safe.

 TODO: index annotation, index edges

 */

public class IndexedDocument implements StatementAction {



    ProvUtilities u=new ProvUtilities();
    final ProvFactory pFactory;


    private HashMap<QualifiedName,Entity>   entityMap=new HashMap<QualifiedName, Entity>();
    private HashMap<QualifiedName,Activity> activityMap=new HashMap<QualifiedName, Activity>();
    private HashMap<QualifiedName,Agent>    agentMap=new HashMap<QualifiedName, Agent>();

    /* Collection of Used edges that have a given process as an
     * effect. */
    private HashMap<QualifiedName,Collection<Used>> activityUsedMap=new HashMap<QualifiedName, Collection<Used>>();

    /* Collection of Used edges that have a given entity as a
     * cause. */
    private HashMap<QualifiedName,Collection<Used>> entityUsedMap=new HashMap<QualifiedName, Collection<Used>>();
    private Collection<Used> anonUsed=new LinkedList<Used>();
    private HashMap<QualifiedName,Collection<Used>> namedUsedMap=new HashMap<QualifiedName, Collection<Used>>();


    /* Collection of WasGeneratedBy edges that have a given activity as a
     * cause. */
    private HashMap<QualifiedName,Collection<WasGeneratedBy>> activityWasGeneratedByMap=new HashMap<QualifiedName, Collection<WasGeneratedBy>>();

    /* Collection of WasGeneratedBy edges that have a given entity as an
     * effect. */
    private HashMap<QualifiedName,Collection<WasGeneratedBy>> entityWasGeneratedByMap=new HashMap<QualifiedName, Collection<WasGeneratedBy>>();
    private Collection<WasGeneratedBy> anonWasGeneratedBy=new LinkedList<WasGeneratedBy>();
    private HashMap<QualifiedName,Collection<WasGeneratedBy>> namedWasGeneratedByMap=new HashMap<QualifiedName, Collection<WasGeneratedBy>>();


    /* Collection of WasDerivedFrom edges that have a given entity as a cause. */
    private HashMap<QualifiedName,Collection<WasDerivedFrom>> entityCauseWasDerivedFromMap=new HashMap<QualifiedName, Collection<WasDerivedFrom>>();

    /* Collection of WasDerivedFrom edges that have a given entity as an
     * effect. */
    private HashMap<QualifiedName,Collection<WasDerivedFrom>> entityEffectWasDerivedFromMap=new HashMap<QualifiedName, Collection<WasDerivedFrom>>();
    private Collection<WasDerivedFrom> anonWasDerivedFrom=new LinkedList<WasDerivedFrom>();
    private HashMap<QualifiedName,Collection<WasDerivedFrom>> namedWasDerivedFromMap=new HashMap<QualifiedName, Collection<WasDerivedFrom>>();


    /* Collection of WasAssociatedWith edges that have a given activity as an
     * effect. */
    private HashMap<QualifiedName,Collection<WasAssociatedWith>> activityWasAssociatedWithMap=new HashMap<QualifiedName, Collection<WasAssociatedWith>>();

    /* Collection of WasAssociatedWith edges that have a given agent as a
     * cause. */
    private HashMap<QualifiedName,Collection<WasAssociatedWith>> agentWasAssociatedWithMap=new HashMap<QualifiedName, Collection<WasAssociatedWith>>();
    private Collection<WasAssociatedWith> anonWasAssociatedWith=new LinkedList<WasAssociatedWith>();
    private HashMap<QualifiedName,Collection<WasAssociatedWith>> namedWasAssociatedWithMap=new HashMap<QualifiedName, Collection<WasAssociatedWith>>();


    /* Collection of WasAttributedTo edges that have a given entiy as an
     * effect. */
    private HashMap<QualifiedName,Collection<WasAttributedTo>> entityWasAttributedToMap=new HashMap<QualifiedName, Collection<WasAttributedTo>>();

    /* Collection of WasAttributedTo edges that have a given agent as a
     * cause. */
    private HashMap<QualifiedName,Collection<WasAttributedTo>> agentWasAttributedToMap=new HashMap<QualifiedName, Collection<WasAttributedTo>>();
    private Collection<WasAttributedTo> anonWasAttributedTo=new LinkedList<WasAttributedTo>();
    private HashMap<QualifiedName,Collection<WasAttributedTo>> namedWasAttributedToMap=new HashMap<QualifiedName, Collection<WasAttributedTo>>();


    /* Collection of WasInformedBy edges that have a given activity as a cause. */
    private HashMap<QualifiedName,Collection<WasInformedBy>> activityCauseWasInformedByMap=new HashMap<QualifiedName, Collection<WasInformedBy>>();

    /* Collection of WasInformedBy edges that have a given activity as an
     * effect. */
    private HashMap<QualifiedName,Collection<WasInformedBy>> activityEffectWasInformedByMap=new HashMap<QualifiedName, Collection<WasInformedBy>>();
    private Collection<WasInformedBy> anonWasInformedBy=new LinkedList<WasInformedBy>();
    private HashMap<QualifiedName,Collection<WasInformedBy>> namedWasInformedByMap=new HashMap<QualifiedName, Collection<WasInformedBy>>();

    private Namespace nss;
    private boolean flatten;
    private Collection<ActedOnBehalfOf> anonActedOnBehalfOf;
    private HashMap<QualifiedName, Collection<ActedOnBehalfOf>> namedActedOnBehalfOfMap;
    private HashMap<QualifiedName, Collection<ActedOnBehalfOf>> responsibleActedOnBehalfOfMap;
    private HashMap<QualifiedName, Collection<ActedOnBehalfOf>> delegateActedOnBehalfOfMap;
    private HashMap<QualifiedName, Collection<WasInvalidatedBy>> namedWasInvalidatedByMap;
    private HashMap<QualifiedName, Collection<WasInvalidatedBy>> entityWasInvalidatedByMap;
    private Collection<WasInvalidatedBy> anonWasInvalidatedBy;
    private HashMap<QualifiedName, Collection<WasInvalidatedBy>> activityWasInvalidatedByMap;
    private HashMap<QualifiedName, Collection<SpecializationOf>> namedSpecializationOfMap;
    private HashMap<QualifiedName, Collection<SpecializationOf>> specificEntitySpecializationOfMap;
    private Collection<SpecializationOf> anonSpecializationOf;
    private HashMap<QualifiedName, Collection<SpecializationOf>> genericEntitySpecializationOfMap;
    private Collection<AlternateOf> anonAlternateOf;
    private HashMap<QualifiedName, Collection<AlternateOf>> namedAlternateOfMap;
    private HashMap<QualifiedName, Collection<AlternateOf>> entityCauseAlternateOfMap;
    private HashMap<QualifiedName, Collection<AlternateOf>> entityEffectAlternateOfMap;
    private HashMap<QualifiedName, Collection<WasInfluencedBy>> influenceeWasInfluencedByMap;
    private HashMap<QualifiedName, Collection<WasInfluencedBy>> influencerWasInfluencedByMap;
    private Collection<WasInfluencedBy> anonWasInfluencedBy;
    private HashMap<QualifiedName, Collection<WasInfluencedBy>> namedWasInfluencedByMap;
    private HashMap<QualifiedName, Collection<WasStartedBy>> activityWasStartedByMap;
    private HashMap<QualifiedName, Collection<WasStartedBy>> entityWasStartedByMap;
    private Collection<WasStartedBy> anonWasStartedBy;
    private HashMap<QualifiedName, Collection<WasStartedBy>> namedWasStartedByMap;
    private Collection<WasEndedBy> anonWasEndedBy;
    private HashMap<QualifiedName, Collection<WasEndedBy>> activityWasEndedByMap;
    private HashMap<QualifiedName, Collection<WasEndedBy>> namedWasEndedByMap;
    private HashMap<QualifiedName, Collection<WasEndedBy>> entityWasEndedByMap;
    private Collection<HadMember> anonHadMember;
    private HashMap<QualifiedName, Collection<HadMember>> collHadMemberMap;
    private HashMap<QualifiedName, Collection<HadMember>> namedHadMemberMap;
    private HashMap<QualifiedName, Collection<HadMember>> entityHadMemberMap;
    


    /** Return all used edges for this graph. */
    public Collection<Used> getUsed() {
        return anonUsed;
    }
    /** Return all used edges with activity p as an effect. */
    public Collection<Used> getUsed(Activity p) {
        return activityUsedMap.get(p.getId());
    }

    /** Return all used edges with entity a as a cause. */
    public Collection<Used> getUsed(Entity p) {
        return entityUsedMap.get(p.getId());
    }

    /** Return all WasGeneratedBy edges for this graph. */
    public Collection<WasGeneratedBy> getWasGeneratedBy() {
        return anonWasGeneratedBy;
    }
    /** Return all WasGeneratedBy edges with activity p as an effect. */
    public Collection<WasGeneratedBy> getWasGeneratedBy(Activity p) {
        return activityWasGeneratedByMap.get(p.getId());
    }

    /** Return all WasGeneratedBy edges with entity a as a cause. */
    public Collection<WasGeneratedBy> getWasGeneratedBy(Entity p) {
        return entityWasGeneratedByMap.get(p.getId());
    }

    /** Return all WasDerivedFrom edges for this graph. */
    public Collection<WasDerivedFrom> getWasDerivedFrom() {
        return anonWasDerivedFrom;
    }
    /** Return all WasDerivedFrom edges with entity a as a cause. */
    public Collection<WasDerivedFrom> getWasDerivedFromWithCause(Entity a) {
        return entityCauseWasDerivedFromMap.get(a.getId());
    }

    /** Return all WasDerivedFrom edges with entity a as an effect . */
    public Collection<WasDerivedFrom> getWasDerivedFromWithEffect(Entity a) {
        return entityEffectWasDerivedFromMap.get(a.getId());
    }


    /** Return all WasInformedBy edges for this graph. */
    public Collection<WasInformedBy> getWasInformedBy() {
        return anonWasInformedBy;
    }
    /** Return all WasInformedBy edges with activity p as a cause. */
    public Collection<WasInformedBy> getWasInformedByWithCause(Activity a) {
        return activityCauseWasInformedByMap.get(a.getId());
    }

    /** Return all WasInformedBy edges with activity a as an effect. */
    public Collection<WasInformedBy> getWasInformedByWithEffect(Activity a) {
        return activityEffectWasInformedByMap.get(a.getId());
    }

    /** Return all WasAssociatedWith edges for this graph. */
    public Collection<WasAssociatedWith> getWasAssociatedWith() {
        return anonWasAssociatedWith;
    }
    /** Return all WasAssociatedWith edges with activity p as an effect. */
    public Collection<WasAssociatedWith> getWasAssociatedWith(Activity p) {
        return activityWasAssociatedWithMap.get(p.getId());
    }

    /** Return all WasAssociatedWith edges with entity a as a cause. */
    public Collection<WasAssociatedWith> getWasAssociatedWith(Agent a) {
        return agentWasAssociatedWithMap.get(a.getId());
    
}




    public Entity add(Entity entity) {
        return add(entity.getId(),entity);
    }
    public Entity add(QualifiedName name, Entity entity) {
        Entity existing=entityMap.get(name);
        if (existing!=null) {
            mergeAttributes(existing,entity);
            return existing;
        } else {
            entityMap.put(name,entity);
            return entity;
        }
    }



    void mergeAttributes(Element existing, Element newElement) {
	Set<LangString> set=new HashSet<LangString>(newElement.getLabel());
	set.removeAll(existing.getLabel());
	existing.getLabel().addAll(set);
	
	Set<Location> set2=new HashSet<Location>(newElement.getLocation());
	set2.removeAll(existing.getLocation());
	existing.getLocation().addAll(set2);
	
	Set<Type> set3=new HashSet<Type>(newElement.getType());
	set3.removeAll(existing.getType());
	existing.getType().addAll(set3);
	
	Set<Other> set4=new HashSet<Other>(newElement.getOther());
	set4.removeAll(existing.getOther());
	existing.getOther().addAll(set4);	
    }
    
    void mergeAttributes(Influence existing, Influence newElement) {
	Set<LangString> set=new HashSet<LangString>(newElement.getLabel());
	set.removeAll(existing.getLabel());
	existing.getLabel().addAll(set);
	
	if (existing instanceof HasLocation) {
	    HasLocation existing2=(HasLocation) existing;
	    Set<Location> set2=new HashSet<Location>(((HasLocation)newElement).getLocation());
	    set2.removeAll(existing2.getLocation());
	    existing2.getLocation().addAll(set2);
	}
	
	Set<Type> set3=new HashSet<Type>(newElement.getType());
	set3.removeAll(existing.getType());
	existing.getType().addAll(set3);
	
	Set<Other> set4=new HashSet<Other>(newElement.getOther());
	set4.removeAll(existing.getOther());
	existing.getOther().addAll(set4);	
    }
    <T extends Statement> void mergeAttributes(T existing, T newElement) {
	if (existing instanceof Element) {
	     mergeAttributes((Element) existing, (Element) newElement);
	     return;
	}
	if (existing instanceof Influence) {
	     mergeAttributes((Influence) existing, (Influence) newElement);
	     return;
	}
	throw new UnsupportedOperationException();
    }
    boolean sameEdge(Statement existing, Statement newElement, int count) {
	boolean ok=true;
	for (int i=1; i<=count; i++) {
	    QualifiedName qn1 = (QualifiedName)u.getter(existing,i);
	    QualifiedName qn2 = (QualifiedName)u.getter(newElement,i);
	    if (qn1==null) {
		if (qn2==null) {

		} else {
		    ok=false;
		    break;
		}
	    } else {
		if (qn2==null) {
		    ok=false;
		    break;
		} else {
		    if (!qn1.equals(qn2)) {
			ok=false;
			break;
		    }
		}
	    }
	}
	return ok;
    }
   
    public Agent add(Agent agent) {
        return add(agent.getId(),agent);
    }
    public Agent add(QualifiedName name, Agent agent) {
        Agent existing=agentMap.get(name);
        if (existing!=null) {
            mergeAttributes(existing,agent);
            return existing;
        } else {
            agentMap.put(name,agent);
            return agent;
        }
    }

    public Activity add(Activity activity) {
        return add(activity.getId(),activity);
    }

    public Activity add(QualifiedName name, Activity activity) {
        Activity existing=activityMap.get(name);
        if (existing!=null) {
            mergeAttributes(existing,activity);
            return existing;
        } else {
            activityMap.put(name,activity);
            return activity;
        }
    }

    public Activity getActivity(String name) {
        return activityMap.get(name);
    }
    public Entity getEntity(String name) {
        return entityMap.get(name);
    }
    public Agent getAgent(String name) {
        return agentMap.get(name);
    }
    public IndexedDocument(ProvFactory pFactory, Document doc) {
	this(pFactory,doc,true);
    }
            
    public IndexedDocument(ProvFactory pFactory, Document doc, boolean flatten) {
        this.pFactory=pFactory;
        this.nss=doc.getNamespace();
        this.flatten=flatten;
        
        u.forAllStatementOrBundle(doc.getStatementOrBundle(), this);

    }



    public WasInformedBy add(WasInformedBy wib) {
	return add(wib, 2, anonWasInformedBy, namedWasInformedByMap, activityEffectWasInformedByMap, activityCauseWasInformedByMap);
    }
    public Used add(Used used) {
	return add(used, 2, anonUsed, namedUsedMap, activityUsedMap, entityUsedMap);
    }
    public WasGeneratedBy add(WasGeneratedBy wgb) {
	return add(wgb, 2, anonWasGeneratedBy, namedWasGeneratedByMap, entityWasGeneratedByMap, activityWasGeneratedByMap);
    }
    public WasDerivedFrom add(WasDerivedFrom wdf) {
	return add(wdf, 5, anonWasDerivedFrom, namedWasDerivedFromMap, entityEffectWasDerivedFromMap, entityCauseWasDerivedFromMap);
    }
    public WasAssociatedWith add(WasAssociatedWith waw) {
	return add(waw, 3, anonWasAssociatedWith, namedWasAssociatedWithMap, activityWasAssociatedWithMap, agentWasAssociatedWithMap);
    }
    public WasAttributedTo add(WasAttributedTo wat) {
	return add(wat, 2, anonWasAttributedTo, namedWasAttributedToMap, entityWasAttributedToMap, agentWasAttributedToMap);
    }
    public ActedOnBehalfOf add(ActedOnBehalfOf act) {
	return add(act, 3, anonActedOnBehalfOf, namedActedOnBehalfOfMap, delegateActedOnBehalfOfMap, responsibleActedOnBehalfOfMap);
    }
    public WasInvalidatedBy add(WasInvalidatedBy wib) {
	return add(wib, 2, anonWasInvalidatedBy, namedWasInvalidatedByMap, entityWasInvalidatedByMap, activityWasInvalidatedByMap);
    }
    public SpecializationOf add(SpecializationOf wib) {
	return add(wib, 2, anonSpecializationOf, namedSpecializationOfMap, specificEntitySpecializationOfMap, genericEntitySpecializationOfMap);
    }
    public AlternateOf add(AlternateOf wib) {
	return add(wib, 2, anonAlternateOf, namedAlternateOfMap, entityEffectAlternateOfMap,entityCauseAlternateOfMap);
    }
    public WasInfluencedBy add(WasInfluencedBy winf) {
	return add(winf, 2, anonWasInfluencedBy, namedWasInfluencedByMap, influenceeWasInfluencedByMap, influencerWasInfluencedByMap);
    }
    public WasStartedBy add(WasStartedBy wsb) {
	return add(wsb, 3, anonWasStartedBy, namedWasStartedByMap, activityWasStartedByMap, entityWasStartedByMap);
    }
    public WasEndedBy add(WasEndedBy web) {
	return add(web, 3, anonWasEndedBy, namedWasEndedByMap, activityWasEndedByMap, entityWasEndedByMap);
    }
    public HadMember add(HadMember hm) {
	return add(hm, 2, anonHadMember, namedHadMemberMap, entityHadMemberMap, collHadMemberMap);
    }


    /** Add a wib edge to the graph. Update activityWasInformedByMap and
    agentWasInformedByMap accordingly.  WasInformedBy edges with different attributes are considered distinct.
    */

   public <T extends Relation> T add(T statement,
                                     int num,
                                     Collection<T> anonWasInformedBy,
                                     HashMap<QualifiedName, Collection<T>> namedWasInformedByMap,
                                     HashMap<QualifiedName, Collection<T>> activityEffectWasInformedByMap,
                                     HashMap<QualifiedName, Collection<T>> activityCauseWasInformedByMap) {
	QualifiedName aid2 = u.getEffect(statement); //wib.getInformed();
	QualifiedName aid1 = u.getCause(statement); //wib.getInformant();

	statement = pFactory.newStatement(statement); // clone
	
	QualifiedName id=((Identifiable)statement).getId();

	if (id == null) {

	    boolean found = false;
	    Collection<T> wibcoll = activityEffectWasInformedByMap.get(aid2);
	    if (wibcoll == null) {
		wibcoll = new LinkedList<T>();
		wibcoll.add(statement);
		activityEffectWasInformedByMap.put(aid2, wibcoll);
	    } else {
		for (T u : wibcoll) {
		    if (u.equals(statement)) {
			found = true;
			statement = u;
			break;
		    }
		}
		if (!found) {
		    wibcoll.add(statement);
		}
	    }

	    wibcoll = activityCauseWasInformedByMap.get(aid1);
	    if (wibcoll == null) {
		wibcoll = new LinkedList<T>();
		wibcoll.add(statement);
		activityCauseWasInformedByMap.put(aid1, wibcoll);
	    } else {
		if (!found) {
		    // if we had not found it in the first table, then we
		    // have to add it here too
		    wibcoll.add(statement);
		}
	    }

	    if (!found) {
		anonWasInformedBy.add(statement);
	    }
	} else {
	    Collection<T> wibcoll=namedWasInformedByMap.get(id);
	    if (wibcoll==null) {
		wibcoll=new LinkedList<T>();
		wibcoll.add(statement);
		namedWasInformedByMap.put(id, wibcoll);
	    } else {
		boolean found=false;
		for (T u1: wibcoll) {
		    if (sameEdge(u1,statement,num)) {
			found=true;
			mergeAttributes(u1, statement);
			break;			
		    }
		}
		if (!found) {
		    wibcoll.add(statement);
		}
	    }
	}
	return statement;
}


    @Override
    public void doAction(Activity s) {
	add(s);
	
    }
    @Override
    public void doAction(Used s) {
	add(s);
    }  
    @Override
    public void doAction(WasStartedBy s) {
	add(s);
    }
    @Override
    public void doAction(Agent s) {
	add(s);	
    }
    @Override
    public void doAction(AlternateOf s) {
	add(s);
    }
    @Override
    public void doAction(WasAssociatedWith s) {
	add(s);
    }
    @Override
    public void doAction(WasAttributedTo s) {
	add(s);
    }
    @Override
    public void doAction(WasInfluencedBy s) {
	add(s);
    }
    @Override
    public void doAction(ActedOnBehalfOf s) {
        add(s);
    }
    @Override
    public void doAction(WasDerivedFrom s) {
	add(s);
    }
    @Override
    public void doAction(WasEndedBy s) {
	add(s);
    }
    @Override
    public void doAction(Entity s) {
	add(s);	
    }
    @Override
    public void doAction(WasGeneratedBy s) {
	add(s);
    }
    @Override
    public void doAction(WasInvalidatedBy s) {
	add(s);
    }
    @Override
    public void doAction(HadMember s) {
	add(s);		
    }
    @Override
    public void doAction(MentionOf s) {
	throw new UnsupportedOperationException();			
    }
    @Override
    public void doAction(SpecializationOf s) {
	add(s);
    }
    @Override
    public void doAction(WasInformedBy s) {
	add(s);
    }
    @Override
    public void doAction(DerivedByInsertionFrom s) {
	throw new UnsupportedOperationException();
    }
    @Override
    public void doAction(DictionaryMembership s) {
	throw new UnsupportedOperationException();	
    }
    @Override
    public void doAction(DerivedByRemovalFrom s) {
	throw new UnsupportedOperationException();	
    }

    @Override
    public void doAction(Bundle bun, ProvUtilities provUtilities) {
	if (flatten) {
	    provUtilities.forAllStatement(bun.getStatement(), this);
	} else {
	    throw new UnsupportedOperationException("can't handle bundles without flattening");
	}
    }
    
    public Document toDocument() {
	Document res=pFactory.newDocument();
	res.getStatementOrBundle().addAll(entityMap.values());
	res.getStatementOrBundle().addAll(activityMap.values());
	res.getStatementOrBundle().addAll(agentMap.values());
	res.getStatementOrBundle().addAll(anonUsed);
	for (Collection<Used> c: namedUsedMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}
	res.getStatementOrBundle().addAll(anonWasGeneratedBy);
	for (Collection<WasGeneratedBy> c: namedWasGeneratedByMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}
	res.getStatementOrBundle().addAll(anonWasDerivedFrom);
	for (Collection<WasDerivedFrom> c: namedWasDerivedFromMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}
	res.getStatementOrBundle().addAll(anonWasAssociatedWith);
	for (Collection<WasAssociatedWith> c: namedWasAssociatedWithMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}
	res.getStatementOrBundle().addAll(anonWasAttributedTo);
	for (Collection<WasAttributedTo> c: namedWasAttributedToMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}
	res.getStatementOrBundle().addAll(anonWasInformedBy);
	for (Collection<WasInformedBy> c: namedWasInformedByMap.values()) {
	    res.getStatementOrBundle().addAll(c);
	}

	res.setNamespace(Namespace.gatherNamespaces(res));
	return res;
    }

}

