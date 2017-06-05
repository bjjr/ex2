-- QUERY A

select (select count(s) from Sku s)/count(e) from Event e;

-- QUERY B

select m from Sku s join s.event e join e.manager m group by m order by count(s) desc;

-- Posteriormente se seleccionaría el primer elemento de esta colección.
